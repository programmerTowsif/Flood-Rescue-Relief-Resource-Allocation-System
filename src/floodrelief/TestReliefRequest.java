 
package floodrelief;

import floodrelief.model.EmergencyReliefRequest;
import floodrelief.model.FoodRequest;
import floodrelief.model.MedicalRequest;
import floodrelief.model.MedicalSupply;
import floodrelief.model.Shelter;
import floodrelief.model.User;
import floodrelief.model.FoodSupply;
import floodrelief.model.WaterSupply;
import floodrelief.model.ReliefAllocation;
import floodrelief.service.ReliefManager;
import floodrelief.exception.InsufficientResourceException;

public class TestReliefRequest {

    public static void main(String[] args) {

        // ================= Emergency Relief Request Test =================

        EmergencyReliefRequest request = new EmergencyReliefRequest(
                88, "Rahim", 50, 1,
                "Urgent medical support", 5
        );

        System.out.println("===== Emergency Relief Request =====");
        System.out.println("Request ID: " + request.getRequestId());
        System.out.println("Requester: " + request.getRequesterName());
        System.out.println("People Count: " + request.getPeopleCount());
        System.out.println("Shelter ID: " + request.getShelterId());
        System.out.println("Urgency Level: " + request.getUrgencyLevel());
        System.out.println("Priority: " + request.calculatePriority());
        System.out.println("Request Type: " + request.getRequestType());


        // ================= Food Request Test =================

        FoodRequest food = new FoodRequest(
                101, "towisf", 30, 2,
                "Food support for flood victims", 4, 150
        );

        System.out.println("\n===== Food Request =====");
        System.out.println("Request ID: " + food.getRequestId());
        System.out.println("Requester: " + food.getRequesterName());
        System.out.println("People Count: " + food.getPeopleCount());
        System.out.println("Shelter ID: " + food.getShelterId());
        System.out.println("Urgency Level: " + food.getUrgencyLevel());
        System.out.println("Food Quantity: " + food.getFoodQuantity());
        System.out.println("Priority: " + food.calculatePriority());
        System.out.println("Request Type: " + food.getRequestType());


        // ================= Medical Request Test =================

        MedicalRequest medical = new MedicalRequest(
                102, "towsif", 30, 2,
                "Medicine needed for flood victims",
                "paracetamol", 100
        );

        System.out.println("\n===== Medical Request =====");
        System.out.println("Request ID: " + medical.getRequestId());
        System.out.println("Requester: " + medical.getRequesterName());
        System.out.println("People Count: " + medical.getPeopleCount());
        System.out.println("Shelter ID: " + medical.getShelterId());
        System.out.println("Medicine Name: " + medical.getMedicineName());
        System.out.println("Medicine Quantity: " + medical.getMedicineQuantity());
        System.out.println("Request Type: " + medical.getRequestType());


        // ================= Shelter Test =================

        Shelter shelter = new Shelter(
                1, "Flood Relief Center",
                "Chattogram", 200, 120
        );

        System.out.println("\n===== Shelter Information =====");
        System.out.println("Shelter ID: " + shelter.getShelterId());
        System.out.println("Shelter Name: " + shelter.getShelterName());
        System.out.println("Location: " + shelter.getLocation());
        System.out.println("Capacity: " + shelter.getCapacity());
        System.out.println("Current Occupancy: " + shelter.getCurrentOccupancy());
        System.out.println("Available Space: " + shelter.getAvailableSpace());

        shelter.addPeople(30);
        System.out.println("After Adding 30 People: "
                + shelter.getCurrentOccupancy());

        shelter.removePeople(20);
        System.out.println("After Removing 20 People: "
                + shelter.getCurrentOccupancy());

        System.out.println("Final Available Space: "
                + shelter.getAvailableSpace());


        // ================= Food Supply Test =================

        FoodSupply foodSupply = new FoodSupply(
                201, "Rice", 500, "2027-12-31"
        );

        System.out.println("\n===== Food Supply =====");
        System.out.println("Resource ID: " + foodSupply.getResourceId());
        System.out.println("Resource Name: " + foodSupply.getResourceName());
        System.out.println("Quantity: " + foodSupply.getQuantity());
        System.out.println("Expiry Date: " + foodSupply.getExpiryDate());
        System.out.println("Resource Type: " + foodSupply.getResourceType());

        foodSupply.addQuantity(100);
        System.out.println("After Adding: " + foodSupply.getQuantity());

        foodSupply.reduceQuantity(50);
        System.out.println("After Reducing: " + foodSupply.getQuantity());


        // ================= Water Supply Test =================

        WaterSupply waterSupply = new WaterSupply(
                202, "Drinking Water", 1000, "Liters"
        );

        System.out.println("\n===== Water Supply =====");
        System.out.println("Resource ID: " + waterSupply.getResourceId());
        System.out.println("Resource Name: " + waterSupply.getResourceName());
        System.out.println("Quantity: " + waterSupply.getQuantity());
        System.out.println("Unit: " + waterSupply.getUnit());
        System.out.println("Resource Type: " + waterSupply.getResourceType());

        waterSupply.addQuantity(200);
        System.out.println("After Adding: " + waterSupply.getQuantity());

        waterSupply.reduceQuantity(100);
        System.out.println("After Reducing: " + waterSupply.getQuantity());


        // ================= Medical Supply Test =================

        MedicalSupply medicalSupply = new MedicalSupply(
                203, "Medicine", 500,
                "Paracetamol", "2027-06-30"
        );

        System.out.println("\n===== Medical Supply =====");
        System.out.println("Resource ID: " + medicalSupply.getResourceId());
        System.out.println("Resource Name: " + medicalSupply.getResourceName());
        System.out.println("Medicine Name: " + medicalSupply.getMedicineName());
        System.out.println("Quantity: " + medicalSupply.getQuantity());
        System.out.println("Expiry Date: " + medicalSupply.getExpiryDate());
        System.out.println("Resource Type: " + medicalSupply.getResourceType());

        medicalSupply.addQuantity(100);
        System.out.println("After Adding: " + medicalSupply.getQuantity());

        medicalSupply.reduceQuantity(50);
        System.out.println("After Reducing: " + medicalSupply.getQuantity());


        // ================= Relief Allocation Test =================

        ReliefAllocation allocation = new ReliefAllocation(
                101, 201, 100, "2026-10-31"
        );

        System.out.println("\n===== Relief Allocation =====");
        System.out.println("Request ID: " + allocation.getRequestId());
        System.out.println("Resource ID: " + allocation.getResourceId());
        System.out.println("Allocated Quantity: " + allocation.getAllocatedQuantity());
        System.out.println("Allocation Date: " + allocation.getAllocationDate());


        // ================= Custom Exception Test =================

        System.out.println("\n===== Custom Exception Test =====");

        try {
            throw new InsufficientResourceException(
                    "Not enough resources available."
            );
        } catch (InsufficientResourceException e) {
            System.out.println("Exception Message: " + e.getMessage());
        }


        // ================= Relief Manager Test =================

        System.out.println("\n===== Relief Manager Test =====");

        ReliefManager manager = new ReliefManager();

        FoodRequest managerRequest = new FoodRequest(
                301, "Rahim", 30, 2,
                "Urgent food needed", 4, 150
        );

        manager.addRequest(managerRequest);

        Shelter managerShelter = new Shelter(
                10, "Central Relief Center",
                "Chattogram", 200, 100
        );

        manager.addShelter(managerShelter);

        FoodSupply managerFood = new FoodSupply(
                401, "Rice", 500, "2027-12-31"
        );

        manager.addResource(managerFood);

        System.out.println("Total Requests: "
                + manager.getRequests().size());

        System.out.println("Total Shelters: "
                + manager.getShelters().size());

        System.out.println("Resource Name: "
                + manager.getResource(401).getResourceName());

        System.out.println("Resource Quantity: "
                + manager.getResource(401).getQuantity());


        // ================= Successful Food Allocation Test =================

        System.out.println("\n===== Successful Allocation Test =====");

        try {
            manager.allocateResource(301, 401);

            System.out.println("Allocation Successful!");
            System.out.println("Remaining Food Quantity: "
                    + manager.getResource(401).getQuantity());

            System.out.println("Total Allocations: "
                    + manager.getAllocations().size());

        } catch (InsufficientResourceException e) {
            System.out.println("Allocation Failed: " + e.getMessage());
        }


        // ================= Duplicate Allocation Test =================

        System.out.println("\n===== Duplicate Allocation Test =====");

        try {
            manager.allocateResource(301, 401);

            System.out.println("Allocation Successful!");

        } catch (InsufficientResourceException e) {
            System.out.println("Allocation Failed: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Duplicate Allocation Blocked: "
                    + e.getMessage());
        }

        System.out.println("Remaining Food Quantity: "
                + manager.getResource(401).getQuantity());

        System.out.println("Total Allocations: "
                + manager.getAllocations().size());


        // ================= Insufficient Stock Test =================

        System.out.println("\n===== Insufficient Stock Test =====");

        FoodRequest lowStockRequest = new FoodRequest(
                302, "Karim", 40, 3,
                "Food needed", 4, 400
        );

        FoodSupply lowStockFood = new FoodSupply(
                402, "Rice", 200, "2027-12-31"
        );

        manager.addRequest(lowStockRequest);
        manager.addResource(lowStockFood);

        try {
            manager.allocateResource(302, 402);

            System.out.println("Allocation Successful!");

        } catch (InsufficientResourceException e) {
            System.out.println("Allocation Failed: " + e.getMessage());
        }

        System.out.println("Remaining Food Quantity: "
                + manager.getResource(402).getQuantity());

        System.out.println("Total Allocations: "
                + manager.getAllocations().size());


        // ================= Medical Allocation Test =================

        System.out.println("\n===== Medical Allocation Test =====");

        MedicalRequest medicalRequest = new MedicalRequest(
                303, "Karim", 25, 3,
                "Medicine required", "Paracetamol", 100
        );

        MedicalSupply managerMedical = new MedicalSupply(
                403, "Medicine", 300,
                "Paracetamol", "2027-06-30"
        );

        manager.addRequest(medicalRequest);
        manager.addResource(managerMedical);

        try {
            manager.allocateResource(303, 403);

            System.out.println("Medical Allocation Successful!");

            System.out.println("Remaining Medicine Quantity: "
                    + manager.getResource(403).getQuantity());

            System.out.println("Total Allocations: "
                    + manager.getAllocations().size());

        } catch (InsufficientResourceException e) {
            System.out.println("Medical Allocation Failed: "
                    + e.getMessage());
        }
        

        ReliefManager manager2 = new ReliefManager();
        manager2.registerUser(
    new User(1, "admin", "admin123", "Admin")
);

manager2.registerUser(
    new User(2, "manager", "manager123", "Relief Manager")
);

manager2.registerUser(
    new User(3, "volunteer", "vol123", "Volunteer")
);

User user = manager2.login("manager", "manager123");

if (user != null) {
    System.out.println("Login Successful!");
    System.out.println("Username: " + user.getUsername());
    System.out.println("Role: " + user.getRole());
} else {
    System.out.println("Invalid Login!");
}


    }
}
 