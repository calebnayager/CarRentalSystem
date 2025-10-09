package com.carrental;

import java.util.ArrayList;
import java.util.List;

public class RentalManager {
    private List<Rental> rentals;

    public RentalManager() {
        rentals = new ArrayList<>();
    }

    public void rentVehicle(Vehicle vehicle, String customerName, java.time.LocalDate rentDate, java.time.LocalDate returnDate) {
        Rental rental = new Rental(vehicle, customerName, rentDate, returnDate);
        rentals.add(rental);
    }

    public void returnVehicle(String vehicleId) {
        for (Rental r : rentals) {
            if (r.getVehicle().getVehicleId().equals(vehicleId) && !r.getVehicle().isAvailable()) {
                r.endRental();
                break;
            }
        }
    }

    public List<Rental> getActiveRentals() {
        List<Rental> active = new ArrayList<>();
        for (Rental r : rentals) {
            if (!r.getVehicle().isAvailable()) active.add(r);
        }
        return active;
    }

    public double getTotalRevenue() {
        double total = 0;
        for (Rental r : rentals) {
            total += r.getRentalPrice();
        }
        return total;
    }
}
