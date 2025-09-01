package com.solvd.delivery.model;

import java.time.LocalDate;

public record Promotion(int id, String description, LocalDate validUntil) {
}
