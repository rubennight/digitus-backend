package com.example.technovium.api.productos;

import lombok.Data;

@Data
public class Producto {

    private Integer idProducto;
    private String nombre;
    private Double precio;
    private String categoria;
    private String marca;
    private Integer stock;
    private String imagenProducto;
    
}
