package com.example.technovium.api.pedidos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Tarjeta {

    private Integer idTarjeta;

    private String numeroTarjeta;

    private String banco;

    private LocalDate caducidad;
}
