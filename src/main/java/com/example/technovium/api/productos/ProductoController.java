package com.example.technovium.api.productos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("categoria/{idCategoria}")
    public List<Producto> buscarProductosPorCategoria(@PathVariable Integer idCategoria) {
        return productoService.productoPorCategoria(idCategoria);
    }

    @GetMapping("todos")
    public List<Producto> obtenerTodos() {
        return productoService.obtenerTodosLosProductos();
    }
    
    
}
