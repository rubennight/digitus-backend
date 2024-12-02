package com.example.technovium.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.technovium.persistence.model.DetallePedidoEntity;

public interface DetallePedidoRepository extends JpaRepository<DetallePedidoEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO detalle_pedidos (id_pedido, id_producto, cantidad, folio) VALUES (:idPedido, :idProducto, :cantidad, :folio)", nativeQuery = true)
    public void agregarDetallePedido(
        @Param("idPedido")Integer idPedido, 
        @Param("idProducto") Integer idProducto, 
        @Param("cantidad") Integer cantidad,
        @Param("folio") String folio);

    @Query(value = "SELECT dp FROM DetallePedidoEntity dp WHERE dp.folio = :folio")
    public DetallePedidoEntity encontrarPorFolio(@Param("folio") String folio);
    
}
