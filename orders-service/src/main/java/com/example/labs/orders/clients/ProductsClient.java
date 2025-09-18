package com.example.labs.orders.clients;

import com.example.common.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "products-service")
public interface ProductsClient {

    @GetMapping("/api/products")
    List<Product> all();
}
