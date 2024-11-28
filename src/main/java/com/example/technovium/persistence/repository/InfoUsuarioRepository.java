package com.example.technovium.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.technovium.persistence.model.InfoUsuarioEntity;

public interface InfoUsuarioRepository extends JpaRepository<InfoUsuarioEntity, Integer>{
    
    @Query("SELECT iu FROM InfoUsuarioEntity iu WHERE iu.idUsuario = :idUsuario")
    public List<InfoUsuarioEntity> encontrarPorUsuario(@Param("idUsuario") Integer idUsuario);
    
}
