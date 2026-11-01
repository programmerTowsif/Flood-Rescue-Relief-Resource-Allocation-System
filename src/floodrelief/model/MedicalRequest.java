package floodrelief.model;

public class MedicalRequest extends ReliefRequest{
    private String medicineName;
    private int medicineQuantity;

    public MedicalRequest(int requestId,String requesterName,
        int peopleCount, int shelterId,String description,
       String medicineName,int medicineQuantity
    ){
        super(requestId, requesterName, peopleCount, shelterId, description);
        if(medicineName == null || medicineName.trim().isEmpty()){
            throw new IllegalArgumentException("Medicine name connont be empty");
        }
        if(medicineQuantity<=0){
            throw new IllegalArgumentException("medicine quantity must be positive");

        }
        this.medicineName = medicineName;
        this.medicineQuantity = medicineQuantity;
    }
    // getter for medicine quantity
    public int getMedicineQuantity(){
        return medicineQuantity;
    }
     public String getMedicineName() {
        return medicineName;
    }

    @Override 
    public String getRequestType(){
        return  "Medical Request";
    }
}
