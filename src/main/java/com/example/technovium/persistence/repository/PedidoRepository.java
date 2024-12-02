package com.example.technovium.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.technovium.persistence.model.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer>{

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO pedidos (id_usuario, id_domicilio, id_metodo_pago, id_tarjeta, total, folio, estado_pedido) VALUES ( :idUsuario, :idDomicilio, :idMetodoPago, :idTarjeta, :total, :folio, :estadoPedido)", nativeQuery = true)
    public void registrarPedido(
        @Param("idUsuario") Integer idUsuario, 
        @Param("idDomicilio")Integer idDomicilio, 
        @Param("idMetodoPago")Integer idMetodoPago, 
        @Param("idTarjeta")Integer idTarjeta, 
        @Param("total") Double total,
        @Param("folio") String folio,
        @Param("estadoPedido")String estadoPedido);

    @Query(value = "SELECT p FROM PedidoEntity p WHERE p.folio = :folio")
    public PedidoEntity encontrarPorFolio(@Param("folio") String folio);

}
