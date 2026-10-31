package floodrelief.model;

public class MedicalSupply extends ReliefResource{
    private String medicineName;
    private String expiryDate;

    public MedicalSupply(int resourceId, String resourceName, int quantity, String medicineName, String expiryDate){
        super(resourceId, resourceName, quantity);

        if (medicineName == null || medicineName.trim().isEmpty()) {
            throw new IllegalArgumentException("Medicine name cannot be empty");

        if(expiryDate == null || expiryDate.trim().isEmpty()){
            throw new IllegalArgumentException("Expiry date cannot be empty");
        }

        this.medicineName = medicineName;
        this.expiryDate = expiryDate;
    }
    public String getMedicineName(){
        return medicineName;
    }
    public String getExpiryDate(){
        return expiryDate;
    }
    @Override
    public String getResourceType(){
        return "Medical Supply";
    } 
}