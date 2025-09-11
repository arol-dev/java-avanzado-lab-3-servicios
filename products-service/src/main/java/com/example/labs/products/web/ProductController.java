package com.example.labs.products.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> all() {
        return ResponseEntity.ok(
                List.of(
                        Map.of("id", 1, "name", "Teclado", "price", 20.5),
                        Map.of("id", 2, "name", "Mouse", "price", 10.0)
                )
        );
    }
}
