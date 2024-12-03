package com.example.technovium.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "domicilios")
public class DomicilioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_domicilio")
    private Integer idDomicilio;

    @Column(name = "calle", length = 100)
    private String calle;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "colonia", length = 100)
    private String colonia;

    @Column(name = "codigo_postal")
    private Integer codigoPostal;

    @Column(name = "ciudad", length = 100)
    private String ciudad;

    @Column(name = "estado", length = 100)
    private String estado;

    @Column(name = "telefono", length = 10)
    private String telefono;
}