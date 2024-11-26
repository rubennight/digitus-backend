package com.example.technovium.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.technovium.persistence.model.DetallePedidoEntity;

public interface DetallePedidoRepository extends JpaRepository<DetallePedidoEntity, Integer> {
    
}
