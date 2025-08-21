package com.solvd.delivery.model;

import java.math.BigDecimal;

public record Dish(int id, String name, String description, BigDecimal price, int menuId) {
}
