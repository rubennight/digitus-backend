package com.example.technovium.api.pedidos;

import lombok.Data;

@Data
public class Pedido {
    private Integer idUsuario;
    private Integer idDireccion;
    private String metodoPago;
    private Tarjeta origen;
    private Double total;
}
