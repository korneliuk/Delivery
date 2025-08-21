package com.solvd.delivery.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Order(int id, LocalDateTime orderDate, String status, BigDecimal totalPrice, int clientId) {
}
