package floodrelief.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;
import floodrelief.model.*;
import floodrelief.exception.InsufficientResourceException;

public class ReliefManager {
    private ArrayList<ReliefRequest> requests = new ArrayList<>();
    private ArrayList<Shelter> shelters = new ArrayList<>();
    private ArrayList<ReliefAllocation> allocations = new ArrayList<>();
    private HashMap<Integer, ReliefResource> resources = new HashMap<>();

    public void addRequest(ReliefRequest r) {
        if (r == null) throw new IllegalArgumentException("Request cannot be null.");
        for (ReliefRequest x : requests)
            if (x.getRequestId() == r.getRequestId())
                throw new IllegalArgumentException("Request ID already exists.");
        requests.add(r);
    }
    public void addShelter(Shelter s) {
        if (s == null) throw new IllegalArgumentException("Shelter cannot be null.");
        for (Shelter x : shelters)
            if (x.getShelterId() == s.getShelterId())
                throw new IllegalArgumentException("Shelter ID already exists.");
        shelters.add(s);
    }
    public void addResource(ReliefResource r) {
        if (r == null) throw new IllegalArgumentException("Resource cannot be null.");
        if (resources.containsKey(r.getResourceId()))
            throw new IllegalArgumentException("Resource ID already exists.");
        resources.put(r.getResourceId(), r);
    }
    public ArrayList<ReliefRequest> getRequests() { return new ArrayList<>(requests); }
    public ArrayList<Shelter> getShelters() { return new ArrayList<>(shelters); }
    public ArrayList<ReliefAllocation> getAllocations() { return new ArrayList<>(allocations); }
    public ArrayList<ReliefResource> getResources() { return new ArrayList<>(resources.values()); }
    public ReliefResource getResource(int id) { return resources.get(id); }

    public void allocateResource(int requestId, int resourceId)
            throws InsufficientResourceException {
        ReliefRequest req = null;
        for (ReliefRequest r : requests)
            if (r.getRequestId() == requestId) { req = r; break; }
        if (req == null) throw new IllegalArgumentException("Request not found.");
        for (ReliefAllocation a : allocations)
            if (a.getRequestId() == requestId)
                throw new IllegalArgumentException("This request has already been allocated.");

        ReliefResource res = resources.get(resourceId);
        if (res == null) throw new IllegalArgumentException("Resource not found.");
        int qty;
        LocalDate today = LocalDate.now();

        if (req instanceof FoodRequest) {
            if (!(res instanceof FoodSupply))
                throw new IllegalArgumentException("Food request requires food supply.");
            FoodSupply f = (FoodSupply) res;
            if (LocalDate.parse(f.getExpiryDate()).isBefore(today))
                throw new IllegalArgumentException("Food has expired.");
            qty = ((FoodRequest) req).getFoodQuantity();
        } else if (req instanceof MedicalRequest) {
            if (!(res instanceof MedicalSupply))
                throw new IllegalArgumentException("Medical request requires medical supply.");
            MedicalSupply m = (MedicalSupply) res;
            if (LocalDate.parse(m.getExpiryDate()).isBefore(today))
                throw new IllegalArgumentException("Medicine has expired.");
            MedicalRequest mr = (MedicalRequest) req;
            if (!mr.getMedicineName().equalsIgnoreCase(m.getMedicineName()))
                throw new IllegalArgumentException("Medicine does not match.");
            qty = mr.getMedicineQuantity();
        } else {
            throw new IllegalArgumentException("Unsupported request type for allocation.");
        }
        if (res.getQuantity() < qty)
            throw new InsufficientResourceException("Not enough resources available.");
        res.reduceQuantity(qty);
        allocations.add(new ReliefAllocation(requestId, resourceId, qty, today.toString()));
    }
}