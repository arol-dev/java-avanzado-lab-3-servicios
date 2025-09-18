package com.example.labs.orders.web;

import com.example.labs.orders.clients.ProductsClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final ProductsClient productsClient;

    private final List<Map<String, Object>> orders = List.of(
            Map.of("id", 100, "productId", 1, "date", LocalDate.now().toString()),
            Map.of("id", 101, "productId", 2, "date", LocalDate.now().minusDays(1).toString())
    );

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> hello() {
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOrderByProductId(@PathVariable("id") int id) {
        return orders.stream()
                .filter(o -> (int) o.get("productId") == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/with-products")
    public ResponseEntity<List<Map<String, Object>>> ordersWithProducts() {

        // Enriquecer cada orden con el producto desde ProductsClient
        List<Map<String, Object>> enriched = orders.stream().map(order -> {
            Object product = productsClient.getProductById((Integer) order.get("productId"));
            return Map.of(
                    "id", order.get("id"),
                    "productId", order.get("productId"),
                    "date", order.get("date"),
                    "product", product
            );
        }).toList();

        return ResponseEntity.ok(enriched);
    }

    public OrderController(ProductsClient productsClient) {
        this.productsClient = productsClient;
    }
}
