package com.example.labs.orders.dto;

import com.example.common.dto.Product;

import java.time.LocalDate;

public record OrderWithProduct(
        Integer id,
        Product product,
        LocalDate date
) {
}
