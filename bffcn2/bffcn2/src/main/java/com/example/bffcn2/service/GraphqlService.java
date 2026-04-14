package com.example.bffcn2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GraphqlService {

    private final RestTemplate restTemplate;

    @Value("${azure.functions.graphql.usuarios}")
    private String graphqlUsuariosUrl;

    @Value("${azure.functions.graphql.prestamos}")
    private String graphqlPrestamosUrl;

    public GraphqlService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String consultarUsuarios() {
        String body = """
                {
                  "query": "{ usuarios { id_usuario nombre correo telefono } }"
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(graphqlUsuariosUrl, request, String.class);
    }

    public String consultarPrestamos() {
        String body = """
                {
                  "query": "{ prestamos { id_prestamo id_usuario id_libro fecha_prestamo fecha_devolucion estado } }"
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(graphqlPrestamosUrl, request, String.class);
    }
}