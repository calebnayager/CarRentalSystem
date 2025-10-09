package com.carrental;

import javax.swing.SwingUtilities;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Launch GUI
        SwingUtilities.invokeLater(() -> {
            CarRentalGUI gui = new CarRentalGUI();
            gui.setVisible(true); 
        });

   
        FleetManager fleetManager = new FleetManager();
        RentalManager rentalManager = new RentalManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Car Rental System =====");
            System.out.println("1. View all vehicles");
            System.out.println("2. Add a vehicle");
            System.out.println("3. Remove a vehicle");
            System.out.println("4. Rent a vehicle");
            System.out.println("5. Return a vehicle");
            System.out.println("6. Show available vehicles");
            System.out.println("7. Show active rentals");
            System.out.println("8. Show total revenue");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": // View all vehicles
                    List<Vehicle> allVehicles = fleetManager.getVehicles();
                    if (allVehicles.isEmpty()) System.out.println("No vehicles found.");
                    else allVehicles.forEach(System.out::println);
                    break;

                case "2": // Add vehicle
                    try {
                        System.out.print("Enter vehicle ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter vehicle type: ");
                        String type = scanner.nextLine();
                        System.out.print("Enter model: ");
                        String model = scanner.nextLine();
                        System.out.print("Enter price per day: ");
                        double price = Double.parseDouble(scanner.nextLine());

                        Vehicle vehicle = new Vehicle(id, type, model, price);
                        fleetManager.addVehicle(vehicle);
                        System.out.println("Vehicle added successfully!");
                    } catch (Exception e) {
                        System.out.println("Error adding vehicle. Check inputs.");
                    }
                    break;

                case "3": // Remove vehicle
                    System.out.print("Enter vehicle ID to remove: ");
                    String removeId = scanner.nextLine();
                    fleetManager.removeVehicle(removeId);
                    System.out.println("Vehicle removed (if it existed).");
                    break;

                case "4": // Rent vehicle
                    List<Vehicle> available = fleetManager.getAvailableVehicles();
                    if (available.isEmpty()) {
                        System.out.println("No vehicles available to rent.");
                        break;
                    }
                    System.out.println("--- Available Vehicles ---");
                    for (int i = 0; i < available.size(); i++) {
                        System.out.println(i + 1 + ". " + available.get(i));
                    }
                    try {
                        System.out.print("Select vehicle number to rent: ");
                        int vIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        if (vIndex < 0 || vIndex >= available.size()) {
                            System.out.println("Invalid selection.");
                            break;
                        }
                        Vehicle v = available.get(vIndex);

                        System.out.print("Enter customer name: ");
                        String customer = scanner.nextLine();
                        System.out.print("Enter rent date (YYYY-MM-DD): ");
                        LocalDate rentDate = LocalDate.parse(scanner.nextLine());
                        System.out.print("Enter return date (YYYY-MM-DD): ");
                        LocalDate returnDate = LocalDate.parse(scanner.nextLine());

                        rentalManager.rentVehicle(v, customer, rentDate, returnDate);
                        fleetManager.saveVehicles();
                        System.out.println("Vehicle rented successfully!");
                    } catch (Exception e) {
                        System.out.println("Invalid input. Check dates and selection.");
                    }
                    break;

                case "5": // Return vehicle
                    System.out.print("Enter vehicle ID to return: ");
                    String returnId = scanner.nextLine();
                    rentalManager.returnVehicle(returnId);
                    fleetManager.saveVehicles();
                    System.out.println("Vehicle returned (if it was rented).");
                    break;

                case "6": // Show available vehicles
                    List<Vehicle> availableVehicles = fleetManager.getAvailableVehicles();
                    if (availableVehicles.isEmpty()) System.out.println("No vehicles available.");
                    else {
                        System.out.println("--- Available Vehicles ---");
                        availableVehicles.forEach(System.out::println);
                    }
                    break;

                case "7": // Show active rentals
                    List<Rental> activeRentals = rentalManager.getActiveRentals();
                    if (activeRentals.isEmpty()) System.out.println("No active rentals.");
                    else {
                        System.out.println("--- Active Rentals ---");
                        activeRentals.forEach(System.out::println);
                    }
                    break;

                case "8": // Show total revenue
                    double revenue = rentalManager.getTotalRevenue();
                    System.out.println("Total revenue: R" + revenue);
                    break;

                case "9": // Exit
                    System.out.println("Saving vehicles and exiting...");
                    fleetManager.saveVehicles();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
