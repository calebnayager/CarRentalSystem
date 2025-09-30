package com.carrental;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FleetManager fleetManager = new FleetManager();
        RentalManager rentalManager = new RentalManager();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Car Rental System ===");
            System.out.println("1. Add Vehicle");
            System.out.println("2. List Available Vehicles");
            System.out.println("3. Rent Vehicle");
            System.out.println("4. Return Vehicle");
            System.out.println("5. Show All Rentals + Revenue");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> {
                    System.out.print("Vehicle ID: ");
                    String vid = scanner.nextLine().trim();
                    System.out.print("Type: ");
                    String type = scanner.nextLine().trim();
                    System.out.print("Model: ");
                    String model = scanner.nextLine().trim();
                    System.out.print("Rental price per day: ");
                    double price = Double.parseDouble(scanner.nextLine().trim());
                    fleetManager.addVehicle(new Vehicle(vid, type, model, price));
                }
                case "2" -> {
                    List<Vehicle> available = fleetManager.getAvailableVehicles();
                    if (available.isEmpty()) System.out.println("No vehicles available.");
                    else available.forEach(System.out::println);
                }
                case "3" -> {
                    System.out.print("Vehicle ID: ");
                    String vid = scanner.nextLine().trim();
                    Vehicle vehicle = fleetManager.findVehicleById(vid);
                    if (vehicle == null) System.out.println("Vehicle not found.");
                    else {
                        System.out.print("Customer name: ");
                        String cname = scanner.nextLine().trim();
                        System.out.print("Rental date (YYYY-MM-DD): ");
                        LocalDate rDate = LocalDate.parse(scanner.nextLine().trim());
                        System.out.print("Return date (YYYY-MM-DD): ");
                        LocalDate retDate = LocalDate.parse(scanner.nextLine().trim());
                        rentalManager.rentVehicle(vehicle, cname, rDate, retDate);
                    }
                }
                case "4" -> {
                    System.out.print("Vehicle ID to return: ");
                    String vid = scanner.nextLine().trim();
                    rentalManager.returnVehicle(vid);
                }
                case "5" -> {
                    List<Rental> active = rentalManager.getActiveRentals();
                    if (active.isEmpty()) System.out.println("No active rentals.");
                    else active.forEach(System.out::println);
                    System.out.printf("Total revenue: R%.2f%n", rentalManager.getTotalRevenue());
                }
                case "6" -> exit = true;
                default -> System.out.println("Invalid option.");
            }
        }
        System.out.println("Exiting system. Goodbye!");
        scanner.close();
    }
}
