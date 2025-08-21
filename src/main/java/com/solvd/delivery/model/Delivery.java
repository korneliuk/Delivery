package com.solvd.delivery.model;

import java.time.LocalDateTime;

public record Delivery(int id, LocalDateTime pickupTime, LocalDateTime deliveryTime, String deliveryStatus,
                       double distanceKm, int courierId, int orderId) {
}
