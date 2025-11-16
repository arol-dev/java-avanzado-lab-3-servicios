package com.example.labs.orders.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.labs.orders.clients.ProductsClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private ProductsClient productsClient;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> all() {
        List<Map<String,Object>> products = productsClient.getProducts(); // Llamada al servicio de Products
        products.add(Map.of("name", "Altavoces", "price", 85.0, "id", 3));
        return ResponseEntity.ok(products);
    }
}
