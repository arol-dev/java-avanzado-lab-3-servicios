package com.example.labs.orders.dto;

import java.time.LocalDate;

public record Order(Integer id, Integer productId, LocalDate date) {
}
