package com.example.technovium.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.technovium.persistence.model.InfoUsuarioEntity;

public interface InfoUsuarioRepository extends JpaRepository<InfoUsuarioEntity, Integer>{
    
    @Query("SELECT iu FROM InfoUsuarioEntity iu WHERE iu.idUsuario = :idUsuario")
    public List<InfoUsuarioEntity> encontrarPorUsuario(@Param("idUsuario") Integer idUsuario);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO info_usuarios (id_usuario, id_domicilio) VALUES (:idUsuario, :idDomicilio)", nativeQuery = true)
    public void registrarInfoUsuario(
        @Param("idUsuario")Integer idUsuario, 
        @Param("idDomicilio")Integer idDomicilio);

}
