package com.solvd.delivery.model;

public class Vehicle {
    private int id;
    private String type;
    private String plateNumber;
    private int courierId;

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }
}
