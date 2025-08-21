package com.solvd.delivery.model;

public record Rating(int id, int score, String comment, int clientId, int courierId, int orderId) {
}
