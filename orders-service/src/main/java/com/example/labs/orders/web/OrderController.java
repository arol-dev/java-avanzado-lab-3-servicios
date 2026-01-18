package com.example.labs.orders.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.labs.orders.clients.ProductsClient;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final ProductsClient productsClient;

    public OrderController(ProductsClient productsClient) {
        this.productsClient = productsClient;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> all() {
        return ResponseEntity.ok(
                List.of(
                        Map.of("id", 100, "productId", 1, "date", LocalDate.now().toString()),
                        Map.of("id", 101, "productId", 2, "date", LocalDate.now().minusDays(1).toString())));
    }

    @GetMapping("/{id}/with-products")
    public ResponseEntity<Map<String, Object>> getOrderWithProducts(
            @org.springframework.web.bind.annotation.PathVariable int id) {
        var product = productsClient.getProduct(1); // Lógica Mock: siempre obtiene el producto 1
        return ResponseEntity.ok(
                Map.of(
                        "id", id,
                        "date", LocalDate.now().toString(),
                        "product", product != null ? product : "Product info unavailable"));
    }
}
