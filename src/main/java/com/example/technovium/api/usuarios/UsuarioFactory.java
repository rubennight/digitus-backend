package com.example.technovium.api.usuarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.technovium.api.domcilios.Domicilio;
import com.example.technovium.api.domcilios.DomicilioFactory;
import com.example.technovium.persistence.model.DomicilioEntity;
import com.example.technovium.persistence.model.InfoUsuarioEntity;
import com.example.technovium.persistence.model.UsuarioEntity;
import com.example.technovium.persistence.repository.DomicilioRepository;
import com.example.technovium.persistence.repository.InfoUsuarioRepository;

@Component
public class UsuarioFactory {
    @Autowired
    private InfoUsuarioRepository infoUsuarioRepository;

    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    private DomicilioFactory domicilioFactory;

    public Usuario toUsuario(UsuarioEntity usuarioEntity) {
        
        Usuario usuario = new Usuario();

        usuario.setNombre(usuarioEntity.getNombre());
        usuario.setCorreo(usuarioEntity.getCorreo());

        List<Domicilio> domicilios = obtenerDomicilios(usuarioEntity);
        usuario.setDomicilios(domicilios);

        return usuario;

    }

    public List<Domicilio> obtenerDomicilios(UsuarioEntity usuarioEntity){
        List<InfoUsuarioEntity> infoUsuarioEntities = infoUsuarioRepository.encontrarPorUsuario(usuarioEntity.getIdUsuario());

        List<Domicilio> domicilios = new ArrayList<>();

        if(!infoUsuarioEntities.isEmpty()){
            for (InfoUsuarioEntity infoUsuarioEntity : infoUsuarioEntities) {
                DomicilioEntity domicilioEntity = domicilioRepository.encontrarPorDomicilio(infoUsuarioEntity.getIdDomicilio());
                Domicilio domicilio = domicilioFactory.toObject(domicilioEntity);


                domicilios.add(domicilio);
            }
        }

        return domicilios;
    }

}
