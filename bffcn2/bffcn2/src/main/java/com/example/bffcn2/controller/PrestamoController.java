package com.example.bffcn2.controller;

import com.example.bffcn2.service.PrestamoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService service;

    public PrestamoController(PrestamoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return service.obtener(id);
        }
        return service.listar();
    }

    @PostMapping
    public String crear(@RequestBody String body) {
        return service.crear(body);
    }

    @PutMapping
    public String actualizar(@RequestParam int id, @RequestBody String body) {
        return service.actualizar(id, body);
    }

    @DeleteMapping
    public String eliminar(@RequestParam int id) {
        return service.eliminar(id);
    }
}