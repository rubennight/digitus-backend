package com.example.technovium.api.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.technovium.persistence.model.UsuarioEntity;
import com.example.technovium.persistence.repository.UsuarioRepository;
import com.example.technovium.util.enums.TipoUsuario;

@Service
public class UsuariosService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioFactory usuarioFactory;

    public Usuario iniciarUsuario(NuevoUsuario nuevoUsuario) {
        Usuario usuario = null;
        try {

            UsuarioEntity usuarioEntity = usuarioRepository.buscarUsuarioPorCorreo(nuevoUsuario.getCorreo());

            if(usuarioEntity != null){
                usuario = usuarioFactory.toUsuario(usuarioEntity);
            }else{
                usuario = registrarUsuario(nuevoUsuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return usuario;
    }

    public Usuario registrarUsuario(NuevoUsuario nuevoUsuario){
        usuarioRepository.registrarUsuario(TipoUsuario.CLIENTE.getValue().toLowerCase(), nuevoUsuario.getCorreo(), nuevoUsuario.getNombre());

        UsuarioEntity usuarioEntity = usuarioRepository.buscarUsuarioPorCorreo(nuevoUsuario.getCorreo());

        Usuario usuario = usuarioFactory.toUsuario(usuarioEntity);

        return usuario;
    }

}
