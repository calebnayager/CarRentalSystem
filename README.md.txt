Car Rental System Project

Hey! This is my simple Car Rental System made in Java. It’s a console-based program that lets you add vehicles, rent them out, return them, and see the total revenue. I built this using Java classes, without any external database, so everything runs in memory while the program is open.

Features:
1-Add new vehicles with ID, type, model, and rental price per day
2-Check which vehicles are available to rent
3-Rent a vehicle to a customer and specify rental dates
4-Return a vehicle and calculate the total cost
5-View all active rentals and the total revenue

How I Built It:
I used object-oriented programming in Java:

-Vehicle.java – holds vehicle details

-FleetManager.java – manages the list of vehicles and availability

-Rental.java – represents a rental transaction

-RentalManager.java – handles renting, returning, and revenue calculations

-Main.java – the console interface to interact with the system

Sample Vehicles
You can use these to test the system:
ID: V001, Type: Car, Model: Honda Civic, Price per day: 500
ID: V002, Type: Van, Model: Ford Transit, Price per day: 800
ID: V003, Type: Truck, Model: Isuzu D-Max, Price per day: 1000
ID: V004, Type: Car, Model: BMW 320i, Price per day: 1200

(You can add more if you want!)

How to Run It:
1.Open jGRASP and open Main.java.
2.Click Run – the console menu will show up.
3.Follow the menu options to add vehicles, rent, return, or view revenue.

When you’re done, just close the console – everything is saved in memory while the program runs.