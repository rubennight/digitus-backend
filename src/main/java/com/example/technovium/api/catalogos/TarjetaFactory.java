package com.example.technovium.api.catalogos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.technovium.api.pedidos.Tarjeta;
import com.example.technovium.persistence.model.TarjetaEntity;

@Component
public class TarjetaFactory {

    public Tarjeta toObject(TarjetaEntity tarjetaEntity){
        Tarjeta tarjeta = new Tarjeta();

        tarjeta.setIdTarjeta(tarjetaEntity.getIdTarjeta());
        tarjeta.setNumeroTarjeta(tarjetaEntity.getNumeroTarjeta());
        tarjeta.setBanco(tarjetaEntity.getBanco());
        tarjeta.setCaducidad(tarjetaEntity.getCaducidad());

        return tarjeta;
    }

    public List<Tarjeta> toObjects(List<TarjetaEntity> tarjetaEntities){
        List<Tarjeta> tarjetas = new ArrayList<>();

        for (TarjetaEntity tarjetEntity : tarjetaEntities) {
            Tarjeta tarjeta = toObject(tarjetEntity);

            tarjetas.add(tarjeta);
        }

        return tarjetas;
    }
}
