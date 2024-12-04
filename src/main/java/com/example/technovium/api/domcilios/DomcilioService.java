package com.example.technovium.api.domcilios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.technovium.persistence.model.DomicilioEntity;
import com.example.technovium.persistence.model.InfoUsuarioEntity;
import com.example.technovium.persistence.repository.DomicilioRepository;
import com.example.technovium.persistence.repository.InfoUsuarioRepository;

@Service
public class DomcilioService {
    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    private DomicilioFactory domicilioFactory;

    @Autowired
    private InfoUsuarioRepository infoUsuarioRepository;

    @Transactional
    public Domicilio persistirDomicilio(AgregarDomicilio domicilio) {

        Domicilio domicilioValidado = validarDomicilio(domicilio);

        if(domicilioValidado != null){
            domicilioValidado.setYaExistia(true);
            return domicilioValidado;
        }else{
            domicilioValidado = registrarDomicilio(domicilio);
            domicilioValidado.setYaExistia(false);
            return domicilioValidado;
        }

    }

    public Domicilio validarDomicilio(AgregarDomicilio domicilio){
        Domicilio domicilioDTO = null;

        List<InfoUsuarioEntity> infoUsuarioEntities = infoUsuarioRepository.encontrarPorUsuario(domicilio.getIdUsuario());
        List<DomicilioEntity> domicilioEntities = new ArrayList<>();

        try {
                    
            if (!infoUsuarioEntities.isEmpty()) {

                for (InfoUsuarioEntity infoUsuarioEntity : infoUsuarioEntities) {
                    DomicilioEntity domicilioEntity = domicilioRepository.encontrarPorDomicilio(infoUsuarioEntity.getIdDomicilio());

                    domicilioEntities.add(domicilioEntity);
                    }
            }

            if(!domicilioEntities.isEmpty()){
                DomicilioEntity domicilioEntity = domicilioRepository.regresarDomicilio(domicilio.getNumero(), domicilio.getCodigoPostal(), domicilio.getTelefono());
                if(domicilioEntity != null){
                    domicilioDTO = domicilioFactory.toObject(domicilioEntity);
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
                domicilio.getCalle(), 
                domicilio.getNumero(), 
                domicilio.getColonia(), 
                domicilio.getCodigoPostal(), 
                domicilio.getCiudad(), 
                domicilio.getEstado(), 
                domicilio.getTelefono()
                );
            domicilioRepository.flush();

            DomicilioEntity domicilioEntity = domicilioRepository.regresarDomicilio(
                domicilio.getNumero(), 
                domicilio.getCodigoPostal(), 
                domicilio.getTelefono()
                );

            infoUsuarioRepository.registrarInfoUsuario(
                domicilio.getIdUsuario(),
                domicilioEntity.getIdDomicilio()
            );
            infoUsuarioRepository.flush();

            domicilioDTO = domicilioFactory.toObject(domicilioEntity);       

        } catch (Exception e) {
            e.printStackTrace();
        }         

        return domicilioDTO;
    }
}
