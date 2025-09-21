package com.example.labs.orders.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public record Order(Integer id, Integer productId, LocalDate date) {
}
