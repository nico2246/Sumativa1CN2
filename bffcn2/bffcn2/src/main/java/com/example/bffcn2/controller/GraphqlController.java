package com.example.bffcn2.controller;

import com.example.bffcn2.service.GraphqlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/graphql")
public class GraphqlController {

    private final GraphqlService service;

    public GraphqlController(GraphqlService service) {
        this.service = service;
    }

    @GetMapping("/usuarios")
    public String usuarios() {
        return service.consultarUsuarios();
    }

    @GetMapping("/prestamos")
    public String prestamos() {
        return service.consultarPrestamos();
    }
}