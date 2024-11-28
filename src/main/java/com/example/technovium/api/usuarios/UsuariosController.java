package com.example.technovium.api.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("usuarios")
public class UsuariosController {
    
    @Autowired
    private UsuariosService loginServicio;

    @PostMapping("validarUsuario")
    public Usuario validarUsuario(@RequestBody NuevoUsuario nuevoUsuario){
        return loginServicio.iniciarUsuario(nuevoUsuario);
    }

}
