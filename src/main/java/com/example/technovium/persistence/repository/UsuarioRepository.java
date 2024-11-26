package com.example.technovium.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.technovium.persistence.model.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    
}
