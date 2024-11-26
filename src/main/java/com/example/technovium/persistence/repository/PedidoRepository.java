package com.example.technovium.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.technovium.persistence.model.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer>{
    
}
