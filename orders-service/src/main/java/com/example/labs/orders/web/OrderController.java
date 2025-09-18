package com.example.labs.orders.web;

import com.example.labs.orders.clients.ProductsClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    final private ProductsClient productsClient;

    public OrderController(ProductsClient productsClient) {
        this.productsClient = productsClient;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> all() {
        return ResponseEntity.ok(
                List.of(
                        Map.of("id", 100, "productId", 1, "date", LocalDate.now().toString()),
                        Map.of("id", 101, "productId", 2, "date", LocalDate.now().minusDays(1).toString())
                )
        );
    }

    @GetMapping("/orders-with-products")
    public ResponseEntity<Object> orderWithProducts() {
        List<Map<String, Object>> orders = all().getBody();

        Map<Integer, Map<String, Object>> mapAux = orders.stream()
                .collect(Collectors.toMap(
                        m -> (Integer) m.get("productId"),
                        m -> m
                ));

        List<Map<String, Object>> merged = productsClient.all().stream()
                .map(pro -> {
                    Integer id = (Integer) pro.get("id");
                    Map<String, Object> order = mapAux.get(id);
                    return Map.of(
                            "id", pro.get("id"),
                            "name", pro.get("name"),
                            "date", order.get("date"),
                            "price", pro.get("price")
                    );
                })
                .toList();

        return ResponseEntity.ok(merged);
    }
}
