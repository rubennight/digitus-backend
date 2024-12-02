package com.example.technovium.api.pedidos;

import lombok.Data;

@Data
public class Pedido {
    private Integer idUsuario;
    private Integer idDireccion;
    private Integer idMetodoPago;
    private Tarjeta tarjeta;
    private Double total;
}
