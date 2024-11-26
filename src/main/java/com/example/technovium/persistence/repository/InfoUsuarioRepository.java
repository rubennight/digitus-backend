package com.example.technovium.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.technovium.persistence.model.InfoUsuarioEntity;

public interface InfoUsuarioRepository extends JpaRepository<InfoUsuarioEntity, Integer>{
    
}
