package com.carrental;

public class Vehicle {
    private String vehicleId;
    private String type;
    private String model;
    private double rentalPricePerDay;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String type, String model, double rentalPricePerDay) {
        this.vehicleId = vehicleId;
        this.type = type;
        this.model = model;
        this.rentalPricePerDay = rentalPricePerDay;
        this.isAvailable = true; // default available
    }

    public String getVehicleId() { return vehicleId; }
    public String getType() { return type; }
    public String getModel() { return model; }
    public double getRentalPricePerDay() { return rentalPricePerDay; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return type + " " + model + " (ID: " + vehicleId + ") - " + (isAvailable ? "Available" : "Rented");
    }
}
