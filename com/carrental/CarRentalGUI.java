package com.carrental;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.List;

public class CarRentalGUI extends JFrame {

    private FleetManager fleetManager;
    private RentalManager rentalManager;

    private JTextArea displayArea;
    private JComboBox<String> vehicleBox;
    private JTextField customerField;
    private JTextField rentDateField;
    private JTextField returnDateField;

    public CarRentalGUI() {
        fleetManager = new FleetManager();
        rentalManager = new RentalManager();

        
        setTitle("Car Rental System");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Can add Vehicles
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JTextField vehicleIdField = new JTextField(5);
        JTextField typeField = new JTextField(5);
        JTextField modelField = new JTextField(5);
        JTextField priceField = new JTextField(5);
        JButton addVehicleButton = new JButton("Add Vehicle");

        topPanel.add(new JLabel("ID:"));
        topPanel.add(vehicleIdField);
        topPanel.add(new JLabel("Type:"));
        topPanel.add(typeField);
        topPanel.add(new JLabel("Model:"));
        topPanel.add(modelField);
        topPanel.add(new JLabel("Price/day:"));
        topPanel.add(priceField);
        topPanel.add(addVehicleButton);

        add(topPanel, BorderLayout.NORTH);

        //The display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        //Rent/Return + Action Buttons 
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS)); 

        //Rent/Return Section
        JPanel rentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        vehicleBox = new JComboBox<>();
        customerField = new JTextField(10);
        rentDateField = new JTextField(8);
        returnDateField = new JTextField(8);
        JButton rentButton = new JButton("Rent Vehicle");
        JButton returnButton = new JButton("Return Vehicle");

        rentPanel.add(new JLabel("Select Vehicle:"));
        rentPanel.add(vehicleBox);
        rentPanel.add(new JLabel("Customer:"));
        rentPanel.add(customerField);
        rentPanel.add(new JLabel("Rent Date:"));
        rentPanel.add(rentDateField);
        rentPanel.add(new JLabel("Return Date:"));
        rentPanel.add(returnDateField);
        rentPanel.add(rentButton);
        rentPanel.add(returnButton);

        bottomPanel.add(rentPanel);

        //Action Buttons Section
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JButton showAvailableButton = new JButton("Show Available Vehicles");
        JButton showActiveButton = new JButton("Show Active Rentals");
        JButton showRevenueButton = new JButton("Show Rentals + Revenue");

        actionPanel.add(showAvailableButton);
        actionPanel.add(showActiveButton);
        actionPanel.add(showRevenueButton);

        bottomPanel.add(actionPanel);

        add(bottomPanel, BorderLayout.SOUTH);

        //Actions
        addVehicleButton.addActionListener(e -> {
            try {
                String vid = vehicleIdField.getText().trim();
                String type = typeField.getText().trim();
                String model = modelField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());

                Vehicle v = new Vehicle(vid, type, model, price);
                fleetManager.addVehicle(v);
                fleetManager.saveVehicles();

                JOptionPane.showMessageDialog(this, "Vehicle added!");
                vehicleIdField.setText("");
                typeField.setText("");
                modelField.setText("");
                priceField.setText("");

                updateVehicleBox();
                displayAvailableVehicles();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding vehicle. Check inputs.");
            }
        });

        rentButton.addActionListener(e -> {
            int selectedIndex = vehicleBox.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Select a vehicle to rent!");
                return;
            }

            List<Vehicle> available = fleetManager.getAvailableVehicles();
            Vehicle v = available.get(selectedIndex);

            try {
                String customer = customerField.getText().trim();
                LocalDate rentDate = LocalDate.parse(rentDateField.getText().trim());
                LocalDate returnDate = LocalDate.parse(returnDateField.getText().trim());

                rentalManager.rentVehicle(v, customer, rentDate, returnDate);
                fleetManager.saveVehicles();

                JOptionPane.showMessageDialog(this, "Vehicle rented!");
                updateVehicleBox();
                displayAvailableVehicles();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Use format YYYY-MM-DD.");
            }
        });

        returnButton.addActionListener(e -> {
            String vid = JOptionPane.showInputDialog(this, "Enter Vehicle ID to return:");
            if (vid != null && !vid.isEmpty()) {
                rentalManager.returnVehicle(vid);
                fleetManager.saveVehicles();
                updateVehicleBox();
                displayAvailableVehicles();
            }
        });

        showAvailableButton.addActionListener(e -> displayAvailableVehicles());

        showActiveButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("--- Vehicle Status ---\n");
            List<Vehicle> allVehicles = fleetManager.getVehicles();
            List<Rental> activeRentals = rentalManager.getActiveRentals();

            for (Vehicle v : allVehicles) {
                boolean isRented = !v.isAvailable();
                sb.append(v).append(" | Status: ").append(isRented ? "Rented" : "Available").append("\n");
            }

            sb.append("\n--- Active Rentals Details ---\n");
            if (activeRentals.isEmpty()) sb.append("No active rentals.\n");
            else activeRentals.forEach(r -> sb.append(r).append("\n"));

            displayArea.setText(sb.toString());
        });

        showRevenueButton.addActionListener(e -> {
            List<Rental> all = rentalManager.getActiveRentals();
            StringBuilder sb = new StringBuilder("--- Rentals + Revenue ---\n");
            if (all.isEmpty()) sb.append("No rentals yet.\n");
            else all.forEach(r -> sb.append(r).append("\n"));
            sb.append("\nTotal revenue: R").append(rentalManager.getTotalRevenue()).append("\n");
            displayArea.setText(sb.toString());
        });

        //To save vehicles 
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fleetManager.saveVehicles();
            }
        });

        
        updateVehicleBox();
        displayAvailableVehicles();
    }

   //Shows available vehicles and update vehicles
    private void updateVehicleBox() {
        vehicleBox.removeAllItems();
        List<Vehicle> available = fleetManager.getAvailableVehicles();
        for (Vehicle v : available) {
            vehicleBox.addItem(v.toString() + " | Price/day: R" + v.getRentalPricePerDay());
        }
    }

    private void displayAvailableVehicles() {
        StringBuilder sb = new StringBuilder("--- Available Vehicles ---\n");
        List<Vehicle> available = fleetManager.getAvailableVehicles();
        if (available.isEmpty()) sb.append("No vehicles available.\n");
        else for (Vehicle v : available) {
            sb.append(v).append(" | Price/day: R").append(v.getRentalPricePerDay()).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    //main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CarRentalGUI gui = new CarRentalGUI();
            gui.setVisible(true);
        });
    }
}
