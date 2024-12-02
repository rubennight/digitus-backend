package com.example.technovium.api.tarjetas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.technovium.api.pedidos.Tarjeta;
import com.example.technovium.persistence.model.TarjetaEntity;
import com.example.technovium.persistence.repository.TarjetaRepository;

@Service
public class TarjetaService {
    
    @Autowired
    private TarjetaRepository tarjetaRepository;

    public TarjetaEntity guardarTarjeta(Tarjeta tarjeta){
        TarjetaEntity tarjetaEntity = new TarjetaEntity();
        try {

            tarjetaEntity = tarjetaRepository.encontrarTarjetaPorNumero(tarjeta.getNumeroTarjeta());

            if(tarjetaEntity != null){
                return tarjetaEntity;
            }else{
                tarjetaRepository.registrarTarjeta(tarjeta.getNumeroTarjeta(), tarjeta.getBanco(), tarjeta.getCaducidad());
                tarjetaEntity = tarjetaRepository.encontrarTarjetaPorNumero(tarjeta.getNumeroTarjeta()); 
            }            

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tarjetaEntity;
    }
}
