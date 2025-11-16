package com.example.labs.products.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO (Estudiantes): Completar la configuración de Feign para llamar al servicio de Orders.
 * - Usar serviceId (name) o url. Para Eureka + Gateway se recomienda name="orders-service".
 * - Declarar el endpoint remoto: GET /api/orders
 */
@FeignClient(name = "orders-service")
public interface OrdersClient {

    @GetMapping("/api/orders")
    public String getOrders();

}
