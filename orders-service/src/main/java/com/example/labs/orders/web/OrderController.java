package com.example.labs.orders.web;

import com.example.labs.orders.clients.ProductsClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    ProductsClient productsClient;

    public OrderController(ProductsClient productsClient) {
        this.productsClient = productsClient;
    }

    @GetMapping("/orders-with-products")
    public ResponseEntity<List<Map<String, Object>>> allWithProducts() {
        List<Map<String, Object>> orders = getAllOrders();
        List<Map<String, Object>> products = productsClient.all();

        Map<Integer, Map<String, Object>> productsMap = products.stream()
                .collect(Collectors.toMap(
                        p -> (Integer) p.get("id"),
                        p -> p
                ));

        List<Map<String, Object>> ordersWithProducts = orders.stream()
                .map(order -> {
                    Integer productId = (Integer) order.get("productId");
                    Map<String, Object> product = productsMap.get(productId);

                    Map<String, Object> orderWithProduct = new HashMap<>(order);
                    if (product != null) {
                        orderWithProduct.put("name", product.get("name"));
                        orderWithProduct.put("price", product.get("price"));
                    }
                    return orderWithProduct;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(
                ordersWithProducts
        );
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> all() {
        return ResponseEntity.ok(
                getAllOrders()
        );
    }

    private List<Map<String, Object>> getAllOrders(){
        return List.of(
                Map.of("id", 100, "productId", 1, "date", LocalDate.now().toString()),
                Map.of("id", 101, "productId", 2, "date", LocalDate.now().minusDays(1).toString())
        );
    }
}
