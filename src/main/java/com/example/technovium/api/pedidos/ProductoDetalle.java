package com.example.technovium.api.pedidos;

import lombok.Data;

@Data
public class ProductoDetalle {

    private Integer idProducto;
    private String nombre;
    private Double precio;
    private String categoria;
    private String marca;
    private Integer stock;
    private String imagenProducto;
    private Integer cantidad;
    
}
