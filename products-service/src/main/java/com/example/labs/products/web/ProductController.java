package com.example.labs.products.web;

import com.example.labs.products.clients.OrdersClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final OrdersClient ordersClient;

    public ProductController(OrdersClient ordersClient) {
        this.ordersClient = ordersClient;

    }

    private final List<Map<String, Object>> products = List.of(
            Map.of("id", 1, "name", "Teclado", "price", 20.5),
            Map.of("id", 2, "name", "Mouse", "price", 10.0)
    );

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllProducts() {
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProductById(@PathVariable("id") int id) {
        return products.stream()
                .filter(p -> (int) p.get("id") == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/with-order")
    public ResponseEntity<List<Map<String, Object>>> ordersWithOrder() {
        // Enriquecer cada orden con el producto desde ProductsClient
        List<Map<String, Object>> enriched = products.stream().map(product -> {
            Object order = ordersClient.getOrderByProductId((Integer) product.get("id"));
            return Map.of(
                    "id", product.get("id"),
                    "name", product.get("name"),
                    "price", product.get("price"),
                    "order", order
            );
        }).toList();

        return ResponseEntity.ok(enriched);
    }
}
