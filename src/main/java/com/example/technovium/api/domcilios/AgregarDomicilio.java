package com.example.technovium.api.domcilios;

import lombok.Data;

@Data
public class AgregarDomicilio {
    
    private Integer idUsuario;

    private String calle;

    private Integer numero;

    private String colonia;

    private Integer codigoPostal;

    private String ciudad;

    private String estado;

    private String telefono ;
}
