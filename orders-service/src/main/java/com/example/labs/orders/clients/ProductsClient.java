package com.example.labs.orders.clients;

import com.example.labs.orders.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * TODO (Estudiantes): Configurar Feign para invocar al servicio de Products.
 * - Usar serviceId (name) recomendando name="products-service" para discovery via Eureka.
 * - Declarar el endpoint remoto: GET /api/products
 */
@FeignClient(name = "products-service")
public interface ProductsClient {

    @GetMapping("/api/products")
    List<Product> getAllProducts();

}
