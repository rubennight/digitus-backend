package com.example.technovium.api.productos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.technovium.persistence.model.ProductoEntity;

@Component
public class ProductoFactory {
    
    public Producto toObject(ProductoEntity productoEntity, String categoria){
        Producto producto = new Producto();

        producto.setIdProducto(productoEntity.getIdProducto());
        producto.setNombre(productoEntity.getNombre());
        producto.setPrecio(productoEntity.getPrecio());
        producto.setCategoria(categoria);
        producto.setMarca(productoEntity.getMarca());
        producto.setStock(productoEntity.getStock());
        producto.setImagenProducto(productoEntity.getImagenProducto());

        return producto;

    }

    public List<Producto> toObjects(List<ProductoEntity> productoEntities, String categoria){
        List<Producto> productos = new ArrayList<>();

        for (ProductoEntity productoEntity : productoEntities) {
            Producto producto = toObject(productoEntity, categoria);

            productos.add(producto);
        }

        return productos;
    }
}
