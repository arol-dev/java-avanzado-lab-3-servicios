package com.example.labs.orders.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> all() {
        return ResponseEntity.ok(
                List.of(
                        Map.of("id", 100, "productId", 1, "date", LocalDate.now().toString()),
                        Map.of("id", 101, "productId", 2, "date", LocalDate.now().minusDays(1).toString())
                )
        );
    }
}
