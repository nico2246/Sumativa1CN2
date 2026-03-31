package com.example.bffcn2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioService {

    private final RestTemplate restTemplate;

    @Value("${azure.functions.usuarios}")
    private String usuariosUrl;

    public UsuarioService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String listar() {
        return restTemplate.getForObject(usuariosUrl, String.class);
    }

    public String obtener(int id) {
        return restTemplate.getForObject(usuariosUrl + "?id=" + id, String.class);
    }

    public String crear(String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(usuariosUrl, request, String.class);
    }

    public String actualizar(int id, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        restTemplate.exchange(usuariosUrl + "?id=" + id, HttpMethod.PUT, request, String.class);

        return "{\"mensaje\":\"Actualizado\"}";
    }

    public String eliminar(int id) {
        restTemplate.delete(usuariosUrl + "?id=" + id);
        return "{\"mensaje\":\"Eliminado\"}";
    }
}