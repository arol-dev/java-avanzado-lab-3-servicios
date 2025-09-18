package com.example.labs.orders.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name = "products-service")
public interface ProductsClient {

    @GetMapping("/api/products")
    List<Map<String, Object>> all();
}
