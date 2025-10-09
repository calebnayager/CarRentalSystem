package com.carrental;

import java.io.Serializable;
import java.time.LocalDate;

public class Rental implements Serializable {
    private Vehicle vehicle;
    private String customerName;
    private LocalDate rentDate;
    private LocalDate returnDate;

    public Rental(Vehicle vehicle, String customerName, LocalDate rentDate, LocalDate returnDate) {
        this.vehicle = vehicle;
        this.customerName = customerName;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        vehicle.setAvailable(false);
    }

    public Vehicle getVehicle() { return vehicle; }
    public String getCustomerName() { return customerName; }
    public LocalDate getRentDate() { return rentDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void endRental() {
        vehicle.setAvailable(true);
    }

    public double getRentalPrice() {
        long days = java.time.temporal.ChronoUnit.DAYS.between(rentDate, returnDate);
        days = days <= 0 ? 1 : days; // minimum rental 1 day
        return days * vehicle.getRentalPricePerDay();
    }

    @Override
    public String toString() {
        return vehicle.getType() + " " + vehicle.getModel() + " | Customer: " + customerName +
                " | From: " + rentDate + " To: " + returnDate + " | Price: R" + getRentalPrice();
    }
}
