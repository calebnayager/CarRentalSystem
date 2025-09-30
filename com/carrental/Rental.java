package com.carrental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental {
    private Vehicle vehicle;
    private String customerName;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    public Rental(Vehicle vehicle, String customerName, LocalDate rentalDate, LocalDate returnDate) {
        this.vehicle = vehicle;
        this.customerName = customerName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public Vehicle getVehicle() { return vehicle; }
    public String getCustomerName() { return customerName; }
    public LocalDate getRentalDate() { return rentalDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public double calculateCost() {
        long days = ChronoUnit.DAYS.between(rentalDate, returnDate) + 1;
        return days * vehicle.getRentalPricePerDay();
    }

    @Override
    public String toString() {
        return vehicle + " rented by " + customerName + " from " + rentalDate + " to " + returnDate +
                " | Cost: R" + calculateCost();
    }
}
