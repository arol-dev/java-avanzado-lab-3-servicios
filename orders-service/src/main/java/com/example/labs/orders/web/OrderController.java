package com.example.labs.orders.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.labs.orders.clients.ProductsClient;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    ProductsClient productsClient;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> all() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> order1 = new HashMap<>();
        order1.put("id", 100);
        order1.put("productId", 1);
        order1.put("date", LocalDate.now().toString());
        list.add(order1);

        Map<String, Object> order2 = new HashMap<>();
        order2.put("id", 101);
        order2.put("productId", 2);
        order2.put("date", LocalDate.now().minusDays(1).toString());
        list.add(order2);
        
        return ResponseEntity.ok(
                list
        );
    }
    
    @GetMapping("/with-products")
    public ResponseEntity<List<Map<String, Object>>> allWithProducts() {
        List<Map<String, Object>> products = productsClient.all();
        List<Map<String, Object>> orders = all().getBody();
        
        orders.forEach(order -> {
            int productId = (int) order.get("productId");
            products.stream()
                    .filter(product -> (int) product.get("id") == productId)
                    .findFirst()
                    .ifPresent(product -> order.put("product", product));
        });
        return ResponseEntity.ok(
                orders
        );
    }
}
