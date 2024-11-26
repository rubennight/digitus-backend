package com.example.technovium.persistence.model;

import java.time.LocalDate;

import com.example.technovium.util.enums.Banco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tarjetas")
public class TarjetaEntity {

    @Id
    @Column(name = "numero_tarjeta")
    private Integer numeroTarjeta;

    @Enumerated(EnumType.STRING)
    @Column(name = "banco", nullable = false)
    private Banco banco;

    @Column(name = "caducidad")
    private LocalDate caducidad;

}
