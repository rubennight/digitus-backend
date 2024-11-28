package com.example.technovium.api.domcilios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("domicilio")
public class DomicilioController {
    @Autowired
    private DomcilioService domicilioService;

    @PostMapping("registrarDomicilio")
    public Domicilio registrarDomicilio(@RequestBody AgregarDomicilio domicilio) {
        return domicilioService.persistirDomicilio(domicilio);
    }
    

}
