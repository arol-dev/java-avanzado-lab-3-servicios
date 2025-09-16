package com.example.labs.orders.clients;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO (Estudiantes): Configurar Feign para invocar al servicio de Products.
 * - Usar serviceId (name) recomendando name="products-service" para discovery via Eureka.
 * - Declarar el endpoint remoto: GET /api/products
 */
@FeignClient(name = "products-service")
public interface ProductsClient {

    //TODO
    // definir el método para invocar al servicio de products
    @GetMapping("/api/products")
    List<Map<String, Object>> all();
}
