package com.example.labs.orders.clients;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * TODO (Estudiantes): Configurar Feign para invocar al servicio de Products.
 * - Usar serviceId (name) recomendando name="products-service" para discovery via Eureka.
 * - Declarar el endpoint remoto: GET /api/products
 */
@FeignClient(name = "service-id")
public interface ProductsClient {

    //TODO
    // definir el método para invocar al servicio de products
}
