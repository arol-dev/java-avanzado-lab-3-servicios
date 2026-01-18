package com.example.labs.orders.clients;

import java.util.Map;

/**
 * TODO: Configurar Feign para invocar al servicio de Products.
 * - Usar serviceId (name) recomendando name="products-service" para discovery
 * via Eureka.
 * - Declarar el endpoint remoto: GET /api/products
 */
// TODO: Agregar anotación @FeignClient
// @FeignClient(name = "products-service")
public interface ProductsClient {

    // TODO: El estudiante debe mapear esto correctamente a GET /api/products/{id}
    @org.springframework.web.bind.annotation.GetMapping("/api/products/{id}")
    Map<String, Object> getProduct(@org.springframework.web.bind.annotation.PathVariable("id") int id);
}
