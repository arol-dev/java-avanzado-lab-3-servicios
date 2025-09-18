package com.example.labs.orders.dto;

import com.example.common.dto.Product;

public record OrderProduct(Order order, Product product) {
}
