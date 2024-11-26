package com.example.technovium.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioEntity usuario;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "telefono")
    private Integer telefono;

    @ManyToOne
    @JoinColumn(name = "domicilio_usuario", referencedColumnName = "id_domicilio")
    private DomicilioEntity domicilioUsuario;
}

