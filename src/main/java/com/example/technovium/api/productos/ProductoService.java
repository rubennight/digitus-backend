package com.example.technovium.api.productos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.technovium.persistence.model.CategoriaEntity;
import com.example.technovium.persistence.model.ProductoEntity;
import com.example.technovium.persistence.repository.CategoriaRepository;
import com.example.technovium.persistence.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoFactory productoFactory;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Producto> productoPorCategoria(Integer idCategoria) {
        CategoriaEntity categoriaEntity = categoriaRepository.getReferenceById(idCategoria);     
        String categoria = categoriaEntity.getNombreCategoria();
        
        List<ProductoEntity> productoEntities = productoRepository.encontrarPorCategoria(idCategoria);

        List<Producto> productos = productoFactory.toObjects(productoEntities, categoria);
        
        return productos;
    }

}
