package com.example.labs.products.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name = "orders-service")
public interface OrdersClient {

    @GetMapping("/api/orders")
    List<Map<String, Object>> all();
}
