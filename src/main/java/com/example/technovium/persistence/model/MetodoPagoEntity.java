package com.example.technovium.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "metodo_pagos")
public class MetodoPagoEntity {
    
    @Id
    @Column(name = "id_metodo_pago")
    private Integer idMetodoPago;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "referencia")
    private String referencia;
    
}
