package com.example.labs.products.web;

import com.example.labs.products.clients.OrdersClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    final private OrdersClient ordersClient;

    public ProductController(OrdersClient ordersClient) {
        this.ordersClient = ordersClient;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> all() {
        return ResponseEntity.ok(
                List.of(
                        Map.of("id", 1, "name", "Teclado", "price", 20.5),
                        Map.of("id", 2, "name", "Mouse", "price", 10.0)
                )
        );
    }

    @GetMapping("/products-with-orders")
    public ResponseEntity<Object> orderWithProducts() {
        List<Map<String, Object>> products = all().getBody();

        Map<Integer, Map<String, Object>> mapAux = products.stream()
                .collect(Collectors.toMap(
                        m -> (Integer) m.get("id"),
                        m -> m
                ));

        List<Map<String, Object>> merged = ordersClient.all().stream()
                .map(order -> {
                    Integer productId = (Integer) order.get("productId");
                    Map<String, Object> product = mapAux.get(productId);
                    return Map.of(
                            "id", order.get("id"),
                            "name", product.get("name"),
                            "price", product.get("price")
                    );
                })
                .toList();


        return ResponseEntity.ok(merged);
    }
}
