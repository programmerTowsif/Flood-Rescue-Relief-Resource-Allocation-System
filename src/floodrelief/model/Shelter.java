package floodrelief.model;
public class Shelter {
    private int shelterId;
    private String shelterName;
    private String location;
    private int capacity;
    private int currentOccupancy;

    public Shelter(int shelterId,String shelterName,String location,int capacity,int currentOccupancy){
        if(shelterId <=0){
            throw new IllegalArgumentException("shelter id must be positvi");
        }
        if(shelterName == null || shelterName.trim().isEmpty()){
            throw new IllegalArgumentException("shelter name cannot be empty");
        }
        if(location ==null || location.trim().isEmpty()){
            throw new IllegalArgumentException("Location cannot be empty");
        }
        if(capacity <= 0){
            throw new IllegalArgumentException("capacity cann be positive");
        }
        if(currentOccupancy <0 || currentOccupancy > capacity){
            throw new IllegalArgumentException("Invalid current occupancy");
        }
        this.shelterId = shelterId;
        this.shelterName = shelterName;
        this.location = location;
        this.capacity = capacity;
        this.currentOccupancy = currentOccupancy;
    }
    public  int getShelterId(){
        return shelterId;
    }
    public String getShelterName(){
        return shelterName;
    }
    public String getLocation(){
        return location;
    }
    public int getCapacity(){
        return capacity;
    }
    public int getCurrentOccupancy(){
        return currentOccupancy;
    }
    // calculate available space;
    public int getAvailableSpace(){
        return  capacity - currentOccupancy;
    }
    // add people to shelter
    public void addPeople(int people){
        if(people <= 0){
            throw new IllegalArgumentException("people count must be positive");
        }
        if(people > getAvailableSpace()){
            throw new IllegalArgumentException("No enough space in shelter");
        }
        currentOccupancy+=people;
    }
    //remove 
    public void removePeople(int people){
        if(people <=0){
            throw new IllegalArgumentException("people count must be positiv");
        }
        if(people > currentOccupancy){
            throw new IllegalArgumentException("connot remove more people than current occupancy");
        }
        currentOccupancy -= people;
    }
}