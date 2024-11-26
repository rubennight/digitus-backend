package com.example.technovium.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.technovium.persistence.model.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {
    
    @Query("SELECT p FROM ProductoEntity p WHERE p.categoria = :idCategoria")
    List<ProductoEntity> encontrarPorCategoria(@Param("idCategoria") Integer idCategoria);

}
