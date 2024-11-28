package com.example.technovium.api.pedidos;

import java.util.List;

import lombok.Data;

@Data
public class NuevoPedido {

    private Pedido pedido;
    
    private List<DetalleProducto> detalleProducto;

}
