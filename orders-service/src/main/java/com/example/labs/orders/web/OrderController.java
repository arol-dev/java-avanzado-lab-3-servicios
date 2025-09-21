package com.example.labs.orders.web;

import com.example.labs.orders.clients.ProductsClient;
import com.example.labs.orders.dto.Order;
import com.example.labs.orders.dto.OrderWithProduct;
import com.example.labs.orders.dto.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final ProductsClient productsClient;

    private final List<Order> orderList = List.of(
            new Order(100, 2, LocalDate.now()),
            new Order(101, 1, LocalDate.now().minusDays(7))
    );

    public OrderController(ProductsClient productsClient) {
        this.productsClient = productsClient;
    }

    @GetMapping
    public ResponseEntity<List<Order>> all() {
        return ResponseEntity.ok(orderList);
    }

    @GetMapping("/products")
    public ResponseEntity<List<OrderWithProduct>> getOrdersWithProduct(){
        List<Product> products = productsClient.getAllProducts();
        List<OrderWithProduct> response = new ArrayList<>();
        for(Order o : orderList){
            response.add(new OrderWithProduct(o,products.get(o.productId() - 1)));
        }
        return ResponseEntity.ok(response);
    }
}
