package com.example.technovium.api.productos;

import java.util.ArrayList;
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

    public List<Producto> obtenerTodosLosProductos() {
        List<ProductoEntity> productoEntities = productoRepository.todosLosProductos();
        List<Producto> productos = new ArrayList<>();

        for (ProductoEntity productoEntity : productoEntities) {
            CategoriaEntity categoriaEntity = categoriaRepository.getReferenceById(productoEntity.getCategoria());     
            String categoria = categoriaEntity.getNombreCategoria();

            Producto producto = productoFactory.toObject(productoEntity, categoria);

            productos.add(producto);
        }
        
        return productos;
    }

}
