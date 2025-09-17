package com.example.labs.products.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * TODO (Estudiantes): Completar la configuración de Feign para llamar al servicio de Orders.
 * - Usar serviceId (name) o url. Para Eureka + Gateway se recomienda name="orders-service".
 * - Declarar el endpoint remoto: GET /api/orders
 */
@FeignClient(name = "orders-service")
public interface OrdersClient {

    // TODO (Estudiantes): Ajustar el path si es necesario según el controlador remoto
    // definir el método remoto para invocar al servicio de orders

    @GetMapping("/api/orders")
    List<Map<String, Object>> all();

}
