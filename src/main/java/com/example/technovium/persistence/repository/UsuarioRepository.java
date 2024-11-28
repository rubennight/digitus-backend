package com.example.technovium.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.technovium.persistence.model.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    
    @Query("SELECT u FROM UsuarioEntity u WHERE u.correo = :correo")
    public UsuarioEntity buscarUsuarioPorCorreo(@Param("correo") String correo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (nombre, correo, tipo) VALUES (:nombre, :correo, :tipo)", nativeQuery = true)
    public void registrarUsuario(
        @Param("tipo") String tipo, 
        @Param("correo") String correoElectronico, 
        @Param("nombre") String nombre);

    
}
