package com.example.technovium.api.domcilios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.technovium.persistence.model.DomicilioEntity;

@Component
public class DomicilioFactory {
    
    public Domicilio toObject(DomicilioEntity domicilioEntity){
        Domicilio domicilio = new Domicilio();

        domicilio.setIdDomicilio(domicilioEntity.getIdDomicilio());
        domicilio.setCalle(domicilioEntity.getCalle());
        domicilio.setNumero(domicilioEntity.getNumero());
        domicilio.setColonia(domicilioEntity.getColonia());
        domicilio.setCodigoPostal(domicilioEntity.getCodigoPostal());
        domicilio.setCiudad(domicilioEntity.getCiudad());
        domicilio.setEstado(domicilioEntity.getEstado());
        domicilio.setTelefono(domicilioEntity.getTelefono());

        return domicilio;
    }

    public List<Domicilio> toObjects(List<DomicilioEntity> domicilioEntities){
        List<Domicilio> domicilios = new ArrayList<>();
        
        for (DomicilioEntity domicilioEntity : domicilioEntities) {
            Domicilio domicilio = toObject(domicilioEntity);
            domicilios.add(domicilio);
        }

        return domicilios;
    }

    public DomicilioEntity toEntity(AgregarDomicilio domicilio){

        DomicilioEntity domicilioEntity = new DomicilioEntity();
        
        domicilioEntity.setNumero(domicilio.getNumero());
        domicilioEntity.setTelefono(domicilio.getTelefono());
        domicilioEntity.setEstado(domicilio.getEstado());
        domicilioEntity.setColonia(domicilio.getColonia());
        domicilioEntity.setCodigoPostal(domicilio.getCodigoPostal());
        domicilioEntity.setCiudad(domicilio.getCiudad());
        domicilioEntity.setCalle(domicilio.getCalle());

        return domicilioEntity;
    }

}
