 Car Rental System (Java Project)

Hey, 
This is my Car Rental System, a simple Java-based desktop app that lets you manage vehicles, rentals, and returns — kind of like a mini car rental company management tool.
It’s built using Object-Oriented Programming (OOP) concepts and has a GUI (Graphical User Interface) for an easier user experience.

What this project does?

With this system, you can:
-Add, view, and manage vehicles in your fleet

-Rent out vehicles to customers

-Return vehicles and calculate rental costs

-View active and completed rentals

-Save and load data automatically (so you don’t lose anything when closing the app)

How to Run the Project?
1.Make sure you have Java installed (Java 8 or higher).

2.Download the file "CarRentalSystem.jar." on GIthub by clicking on the jar file and selectin goption view raw.

3.Double-click it once downloaded - make sure you have a IDE such as Jgrasp Installed

4.The GUI will open automaticallly 

How to Operate the System?
1. Adding a Vehicle
-Click on “Add Vehicle”
-Enter: Vehicle ID (e.g. V001)
-Make (e.g. Toyota)
-Model (e.g. Corolla)
-Price per day (e.g. 450)
-Click Save/Add - Your new vehicle will now appear in the vehicle list.

 2.Renting a Vehicle
-Go to the Rent Vehicle tab/button.
-Choose a Vehicle ID from the list (make sure it’s available).
-Enter:
1.Customer name
2.Number of rental days
-Click Rent Vehicle
-The system will calculate the total rental cost and mark the car as “Rented.”

3.Returning a Vehicle
-Open the Return Vehicle section.
-Enter the Vehicle ID of the rented car.
-The system will automatically calculate:
1.Days rented
2.Total revenue earned
-Click Return Vehicle - The vehicle will be set back to “Available.”

Viewing Revenue
-You can view your total revenue in the stats or dashboard area.

Data Files
-The system uses two files to store your data:
1.vehicles.dat - keeps your added vehicles
2.rentals.dat - keeps rental info


*Notes

-The system is completely OOP-based (Classes etc).
-All data is saved when the program closes.
-If you want to view the code you can view it on Github through "the com/carrental" folder.

Author
Created by: Caleb Nayager
Goal: To practice OOP, file handling, and GUI design in Java while building something practical and functional.
