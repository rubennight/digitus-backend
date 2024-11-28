package com.example.technovium.api.domcilios;

import lombok.Data;

@Data
public class Domicilio {
    private Integer idDomicilio;

    private String calle;

    private Integer numero;

    private String colonia;

    private Integer codigoPostal;

    private String ciudad;

    private String estado;

    private String telefono;

    private Boolean yaExistia;
}
