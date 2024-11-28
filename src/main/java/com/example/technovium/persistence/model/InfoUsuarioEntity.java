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
@Table(name = "info_usuarios")
public class InfoUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_info_usuario")
    private Integer idInfoUsuario;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_domicilio")
    private Integer idDomicilio;
}

