package com.example.technovium.api.domcilios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.technovium.persistence.model.DomicilioEntity;
import com.example.technovium.persistence.repository.DomicilioRepository;

@Service
public class DomcilioService {
    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    private DomicilioFactory domicilioFactory;

    @Transactional
    public Domicilio persistirDomicilio(AgregarDomicilio domicilio) {

        Domicilio domicilioValidado = validarDomicilio(domicilio);
        Domicilio domicilioNoValidado = new Domicilio();

        if(domicilioValidado != null){
            domicilioValidado.setYaExistia(true);
            return domicilioValidado;
        }else{
            domicilioNoValidado = registrarDomicilio(domicilio);
            domicilioNoValidado.setYaExistia(false);
            return domicilioNoValidado;
        }

    }

    public Domicilio validarDomicilio(AgregarDomicilio domicilio){
        Domicilio domicilioDTO = null;

        List<DomicilioEntity> domicilioEntities = domicilioRepository.encontrarPorUsuario(domicilio.getIdUsuario());
        try {
            if(!domicilioEntities.isEmpty()){
                for (DomicilioEntity domicilioEntity : domicilioEntities) {
                    System.out.println(domicilioEntity.getIdDomicilio());
                    if(domicilioEntity.getCodigoPostal() != null 
                    && (domicilioEntity.getCodigoPostal().equals(domicilio.getCodigoPostal()) && domicilioEntity.getNumero().equals(domicilio.getNumero()))){
                        domicilioDTO = domicilioFactory.toObject(domicilioEntity);
                        break;
                    }
                }
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return domicilioDTO;

    }

    public Domicilio registrarDomicilio(AgregarDomicilio domicilio){
        Domicilio domicilioDTO = new Domicilio();

        try {
            domicilioRepository.registrarDomicilio(
                domicilio.getIdUsuario(), 
                domicilio.getCalle(), 
                domicilio.getNumero(), 
                domicilio.getColonia(), 
                domicilio.getCodigoPostal(), 
                domicilio.getCiudad(), 
                domicilio.getEstado(), 
                domicilio.getTelefono());
            
            domicilioRepository.flush();

            domicilioDTO = validarDomicilio(domicilio);       

        } catch (Exception e) {
            e.printStackTrace();
        }         

        return domicilioDTO;
    }
}
