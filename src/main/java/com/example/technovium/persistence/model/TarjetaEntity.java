package com.example.technovium.persistence.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tarjetas")
public class TarjetaEntity {

    @Id
    @Column(name = "id_tarjeta")
    private Integer idTarjeta;

    @Column(name = "numero_tarjeta")
    private String numeroTarjeta;

    @Column(name = "banco", nullable = false)
    private String banco;

    @Column(name = "caducidad")
    private LocalDate caducidad;

}
