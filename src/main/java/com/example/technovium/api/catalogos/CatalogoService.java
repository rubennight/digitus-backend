package com.example.technovium.api.catalogos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.technovium.api.pedidos.Tarjeta;
import com.example.technovium.persistence.model.MetodoPagoEntity;
import com.example.technovium.persistence.model.PedidoEntity;
import com.example.technovium.persistence.model.TarjetaEntity;
import com.example.technovium.persistence.repository.MetodoPagoRepository;
import com.example.technovium.persistence.repository.PedidoRepository;
import com.example.technovium.persistence.repository.TarjetaRepository;

@Service
public class CatalogoService {
    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private MetodoPagoFactory metodoPagoFactory;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private TarjetaFactory tarjetaFactory;

    public List<MetodoPagoDto> metodosDePago() {
        List<MetodoPagoEntity> metodoPagoEntities = metodoPagoRepository.obtenerTodosLosMetodosDePago();

        List<MetodoPagoDto> metodoPagoDtos = metodoPagoFactory.toObjects(metodoPagoEntities);

        return metodoPagoDtos;
    }

    public List<Tarjeta> obtenerTarjetasUsuario(Integer idUsuario) {
        List<PedidoEntity> pedidoEntities = pedidoRepository.encontrarEntitiesPorUsuario(idUsuario);
        List<Tarjeta> tarjetas = new ArrayList<>();

        List<Integer> idTarjetas = new ArrayList<>();
        List<TarjetaEntity> tarjetaEntities = new ArrayList<>();  

        if(!pedidoEntities.isEmpty()){

            for (PedidoEntity pedidoEntity : pedidoEntities) {
                
                if (idTarjetas.isEmpty()) {
                    idTarjetas.add(pedidoEntity.getIdTarjeta());

                    TarjetaEntity tarjetaEntity = tarjetaRepository.encontrarPorIdTarjeta(pedidoEntity.getIdTarjeta());
                    tarjetaEntities.add(tarjetaEntity);
                }else{
                    for (Integer idTarjeta : idTarjetas) {
                        if(idTarjeta.equals(pedidoEntity.getIdTarjeta())){
                            idTarjetas.add(pedidoEntity.getIdTarjeta());

                            TarjetaEntity tarjetaEntity = tarjetaRepository.encontrarPorIdTarjeta(pedidoEntity.getIdTarjeta());
                            tarjetaEntities.add(tarjetaEntity);
                        }
                    }
                }
            }
        }else{
            return tarjetas;
        }

        tarjetas = tarjetaFactory.toObjects(tarjetaEntities);

        return tarjetas;

    }

}
