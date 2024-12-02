package com.example.technovium.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_domicilio")
    private Integer idDomicilio;

    @Column(name = "id_metodo_pago")
    private Integer idMetodoPago;

    @Column(name = "id_tarjeta")
    private Integer idTarjeta;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "folio", nullable = false, length = 8)
    private String folio;
}