package com.example.labs.products.web;

import com.example.common.dto.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public ResponseEntity<List<Product>> all() {
        return ResponseEntity.ok(
                List.of(
                        new Product(1, "Teclado", 20.5),
                        new Product(2, "Mouse", 10.0)
                )
        );
    }
}
