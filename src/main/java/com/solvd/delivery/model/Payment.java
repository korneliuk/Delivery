package com.solvd.delivery.model;

import java.time.LocalDateTime;

public record Payment(int id, String paymentMethod, String paymentStatus, LocalDateTime paidAt, int orderId) {
}
