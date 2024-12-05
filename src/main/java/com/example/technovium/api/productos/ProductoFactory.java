package com.example.technovium.api.productos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.technovium.api.pedidos.ProductoDetalle;
import com.example.technovium.persistence.model.CategoriaEntity;
import com.example.technovium.persistence.model.ProductoEntity;

@Component
public class ProductoFactory {
    
    public Producto toObject(ProductoEntity productoEntity, CategoriaEntity categoria){
        Producto producto = new Producto();

        producto.setIdProducto(productoEntity.getIdProducto());
        producto.setNombre(productoEntity.getNombre());
        producto.setPrecio(productoEntity.getPrecio());
        producto.setCategoria(categoria.getNombreCategoria());
        producto.setImagenCategoria(categoria.getImagenCategoria());
        producto.setMarca(productoEntity.getMarca());
        producto.setStock(productoEntity.getStock());
        producto.setImagenProducto(productoEntity.getImagenProducto());

        return producto;

    }

    public List<Producto> toObjects(List<ProductoEntity> productoEntities, CategoriaEntity categoria){
        List<Producto> productos = new ArrayList<>();

        for (ProductoEntity productoEntity : productoEntities) {
            Producto producto = toObject(productoEntity, categoria);

            productos.add(producto);
        }

        return productos;
    }

    public ProductoDetalle toObject(ProductoEntity productoEntity, String categoria, Integer cantidad){
        ProductoDetalle producto = new ProductoDetalle();

        producto.setIdProducto(productoEntity.getIdProducto());
        producto.setNombre(productoEntity.getNombre());
        producto.setPrecio(productoEntity.getPrecio());
        producto.setCategoria(categoria);
        producto.setMarca(productoEntity.getMarca());
        producto.setStock(productoEntity.getStock());
        producto.setImagenProducto(productoEntity.getImagenProducto());
        producto.setCantidad(cantidad);

        return producto;
    }
}
