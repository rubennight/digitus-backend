package com.example.technovium.api.usuarios;

import java.util.List;

import com.example.technovium.api.domcilios.Domicilio;

import lombok.Data;

@Data
public class Usuario {

    private Integer idUsuario;
    private String nombre;
    private String correo;
    private String tipo;
    private List<Domicilio> domicilios;

}
