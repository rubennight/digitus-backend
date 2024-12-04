package com.example.technovium.api.pedidos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    
    @PostMapping("nuevoPedido")
    public EstadoNuevoPedido hacerNuevoPedido(@RequestBody NuevoPedido nuevoPedido) {
        return pedidoService.nuevoPedido(nuevoPedido);
    }

    @GetMapping("misPedidos/{idUsuario}")
    public List<DetallePedido> obtenerMisPedidos(@PathVariable Integer idUsuario) {
        return pedidoService.misPedidos(idUsuario);
    }
    
    @GetMapping("todosLosPedidos")
    public List<DetallePedido> obtenerTodosLosPedidos() {
        return pedidoService.todosLosPedidos();
    }
    
}
