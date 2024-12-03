package com.example.technovium.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.technovium.persistence.model.DomicilioEntity;

public interface DomicilioRepository extends JpaRepository<DomicilioEntity, Integer>{

    @Query("SELECT d FROM DomicilioEntity d WHERE d.idDomicilio = :idDomicilio")
    public DomicilioEntity encontrarPorDomicilio(@Param("idDomicilio") Integer idDomicilio);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO domicilios (calle, numero, colonia, codigo_postal, ciudad, estado, telefono) VALUES (:calle, :numero, :colonia, :codigoPostal, :ciudad, :estado, :telefono)", nativeQuery = true)
    public void registrarDomicilio(
        @Param("calle") String calle, 
        @Param("numero") Integer numero, 
        @Param("colonia") String colonia, 
        @Param("codigoPostal") Integer codigoPostal, 
        @Param("ciudad") String ciudad, 
        @Param("estado") String estado, 
        @Param("telefono") String telefono);

    @Query("SELECT d FROM DomicilioEntity d WHERE d.numero = :numero AND d.codigoPostal = :codigoPostal AND d.telefono = :telefono")
    public DomicilioEntity regresarDomicilio(
        @Param("numero")Integer numero, 
        @Param("codigoPostal")Integer codigoPostal, 
        @Param("telefono")String telefono);

} 