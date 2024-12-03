package com.example.technovium.api.catalogos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.technovium.api.pedidos.Tarjeta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("catalogo")
public class CatalogoController {
    
    @Autowired
    private CatalogoService catalogoService;
    
    @GetMapping("metodoPago")
    public List<MetodoPagoDto> obtenerMetodoPago() {
        return catalogoService.metodosDePago();
    }
    
    @GetMapping("tarjetasPorUsuario/{idUsuario}")
    public List<Tarjeta> tarjetasPorUsuario(@PathVariable Integer idUsuario) {
        return catalogoService.obtenerTarjetasUsuario(idUsuario);
    }
    
}
