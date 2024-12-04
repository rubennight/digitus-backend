package com.example.technovium.api.pedidos;

import java.util.List;

import com.example.technovium.api.domcilios.Domicilio;

import lombok.Data;

@Data
public class DetallePedido {

    private String receptorDePedido;

    private String folio;

    private String metodoPago;

    private Domicilio domicilio;

    private String numeroTarjeta;

    private Double total;

    private List<ProductoDetalle> productos;

}
