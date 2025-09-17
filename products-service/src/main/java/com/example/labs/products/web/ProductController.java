package com.example.labs.products.web;

import com.example.labs.products.clients.OrdersClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    OrdersClient ordersClient;

    public ProductController(OrdersClient productsClient) {
        this.ordersClient = productsClient;
    }

    @GetMapping("/products-with-orders")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> allWithProducts() {
        List<Map<String, Object>> products = getAllProducts();
        List<Map<String, Object>> orders = ordersClient.all();

        // Devuelve un objeto con ambas listas o alguna correlación simple.
        Map<String, List<Map<String, Object>>> resultMap = new HashMap<>();
        resultMap.put("products", products);
        resultMap.put("orders", orders);

        return ResponseEntity.ok(
                resultMap
        );
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> all() {
        return ResponseEntity.ok(
                getAllProducts()
        );
    }

    private List<Map<String, Object>> getAllProducts(){
        return List.of(
                Map.of("id", 1, "name", "Teclado", "price", 20.5),
                Map.of("id", 2, "name", "Mouse", "price", 10.0)
        );
    }
}
