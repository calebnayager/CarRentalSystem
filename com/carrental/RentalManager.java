package com.carrental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalManager {
    private List<Rental> rentals = new ArrayList<>();

    public void rentVehicle(Vehicle vehicle, String customerName, LocalDate rentalDate, LocalDate returnDate) {
        if (!vehicle.isAvailable()) {
            System.out.println("[ERROR] Vehicle not available.");
            return;
        }
        Rental rental = new Rental(vehicle, customerName, rentalDate, returnDate);
        rentals.add(rental);
        vehicle.setAvailable(false);
        System.out.println("[OK] Vehicle rented: " + rental);
    }

    public void returnVehicle(String vehicleId) {
        for (Rental r : rentals) {
            if (r.getVehicle().getVehicleId().equals(vehicleId) && !r.getVehicle().isAvailable()) {
                r.getVehicle().setAvailable(true);
                System.out.println("[OK] Vehicle returned: " + r.getVehicle());
                return;
            }
        }
        System.out.println("[ERROR] Vehicle not found or already returned.");
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
            total += r.calculateCost();
        }
        return total;
    }
}
