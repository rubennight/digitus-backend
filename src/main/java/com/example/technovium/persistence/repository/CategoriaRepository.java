package com.example.technovium.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.technovium.persistence.model.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
    
}
