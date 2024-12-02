package com.example.technovium.persistence.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.technovium.persistence.model.TarjetaEntity;

public interface TarjetaRepository extends JpaRepository<TarjetaEntity, Integer>{
    
    @Query("SELECT t FROM TarjetaEntity t WHERE t.numeroTarjeta = :numeroTarjeta")
    public TarjetaEntity encontrarTarjetaPorNumero(@Param("numeroTarjeta") String numeroTarjeta);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tarjetas (numero_tarjeta, banco, caducidad) VALUES (:numeroTarjeta, :banco, :caducidad)", nativeQuery = true)
    public void registrarTarjeta(
        @Param("numeroTarjeta") String numeroTarjeta, 
        @Param("banco") String banco, 
        @Param("caducidad") LocalDate caducidad);
}
