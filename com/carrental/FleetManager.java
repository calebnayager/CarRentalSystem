package com.carrental;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FleetManager {
    private List<Vehicle> vehicles; 
    private final String filePath = "vehicles.dat"; //File where all vehicels will be saved

    public FleetManager() {
        vehicles = new ArrayList<>();
        loadVehicles(); 
    }
    
    public void addVehicle(Vehicle v) { 
        vehicles.add(v); 
        saveVehicles(); 
    }

    public void removeVehicle(String vehicleId) { 
        vehicles.removeIf(v -> v.getVehicleId().equals(vehicleId)); 
        saveVehicles(); 
    }

    public List<Vehicle> getVehicles() { 
        return vehicles;
    }

    public List<Vehicle> getAvailableVehicles() { 
        List<Vehicle> available = new ArrayList<>(); 
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) available.add(v);
        }
        return available;
    }

    //This saves vehicles when program is closed
    public void saveVehicles() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(vehicles);
        } catch (IOException e) {
            System.out.println("Error saving vehicles: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadVehicles() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            vehicles = (List<Vehicle>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading vehicles: " + e.getMessage());
        }
    }
}
