package com.example.technovium.api.pedidos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("pedidos")
public class PedidoController {
    
    @PostMapping("nuevoPedido")
    public Boolean hacerNuevoPedido(@RequestBody NuevoPedido nuevoPedido) {

        return entity;
    }
    
}
