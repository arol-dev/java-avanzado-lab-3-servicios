package com.example.labs.orders.web;

import com.example.labs.orders.clients.ProductsClient;
import com.example.labs.orders.dto.Order;
import com.example.labs.orders.dto.OrderProduct;
import com.example.labs.orders.dto.OrderWithProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final ProductsClient productsClient;

    private final List<Order> orderList = List.of(
            new Order(100, 1, LocalDate.now()),
            new Order(101, 2, LocalDate.now().minusDays(1))
    );

    public OrderController(ProductsClient productsClient) {
        this.productsClient = productsClient;
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> all() {
        return ResponseEntity.ok(orderList);
    }

    @GetMapping("/with-products")
    public ResponseEntity<List<OrderProduct>> ordersWithProducts() {
        var products = productsClient.all();
        return ResponseEntity.ok(
                orderList.stream().map(order -> new OrderProduct(order, products.stream()
                                .filter(product -> product.id().equals(order.productId()))
                                .findFirst().orElseThrow()))
                        .toList()
        );

    }

    @GetMapping("/v2/with-products")
    public ResponseEntity<List<OrderWithProduct>> ordersWithProductsV2() {
        var products = productsClient.all();
        return ResponseEntity.ok(
                orderList.stream().map(order -> new OrderWithProduct(
                                order.id(),
                                products.stream()
                                        .filter(product -> product.id().equals(order.productId()))
                                        .findFirst().orElseThrow(),
                                order.date()
                        )
                ).toList()
        );

    }
}
