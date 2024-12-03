package com.example.technovium.api.catalogos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.technovium.persistence.model.MetodoPagoEntity;

@Component
public class MetodoPagoFactory {

    public MetodoPagoDto toObject(MetodoPagoEntity metodoPagoEntity){
        MetodoPagoDto metodoPagoDto = new MetodoPagoDto();

        metodoPagoDto.setIdMetodoPago(metodoPagoEntity.getIdMetodoPago());
        metodoPagoDto.setDescripcion(metodoPagoEntity.getDescripcion());
        metodoPagoDto.setReferencia(metodoPagoEntity.getReferencia());

        return metodoPagoDto;
    }

    public List<MetodoPagoDto> toObjects(List<MetodoPagoEntity> metodoPagoEntities) {
        List<MetodoPagoDto> metodoPagoDtos = new ArrayList<>();

        for (MetodoPagoEntity metodoPagoEntity : metodoPagoEntities) {
            MetodoPagoDto metodoPagoDto = toObject(metodoPagoEntity);
            metodoPagoDtos.add(metodoPagoDto);
        }

        return metodoPagoDtos;
    }

}
