package com.example.technovium.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.technovium.persistence.model.MetodoPagoEntity;

public interface MetodoPagoRepository extends JpaRepository<MetodoPagoEntity, Integer>{

    @Query("SELECT mp FROM MetodoPagoEntity mp WHERE mp.idMetodoPago = :idMetodoPago")
    MetodoPagoEntity obtenerPorId(@Param("idMetodoPago") Integer idMetodoPago);
    
}
