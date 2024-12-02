package com.example.technovium.api.pedidos;

import lombok.Data;

@Data
public class EstadoNuevoPedido {

    private Boolean seAgregoTarjeta;
    private String estadoTerjeta;

    private Boolean seAgregoPedido;
    private String estadoPedido;

    private Boolean seAgreganDetallesPedidos;
    private String estadoDetallePedido;

    private Boolean seAgregoPedidoCorrectamente;
}
