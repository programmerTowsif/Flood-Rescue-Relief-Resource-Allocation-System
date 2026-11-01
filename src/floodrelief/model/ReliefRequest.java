package floodrelief.model;

public abstract class ReliefRequest {
    // common information for every relif request
    private int requestId;
    private String requesterName;
    private int peopleCount;
    private int shelterId;
    private String description;
    public ReliefRequest(int requestId,String requesterName,
        int peopleCount, int shelterId,String description
    ){
        if(requestId <= 0){
            throw new IllegalArgumentException("Request Id must be positive");
        }
         if(requesterName == null || requesterName.trim().isEmpty()){
        throw new IllegalArgumentException("request name cannot be empty");
    }
    if(peopleCount <=0) {
        throw new IllegalArgumentException("people count must be possible");
    }
    if(shelterId <= 0){
        throw new IllegalArgumentException("shelter id must be positive");
    }


    this.requestId = requestId;
    this.requesterName = requesterName;
    this.peopleCount = peopleCount;
    this.shelterId = shelterId;
    this.description = description;
    }

    // getterMethods
    public int getRequestId(){
        return requestId;
    }
    public String getRequesterName(){
        return  requesterName;
    }
    public int getPeopleCount(){
        return peopleCount;
    }
    public  int getShelterId(){
        return shelterId;
    }
   public String getDescription(){
    return  description;
   }
   public abstract String getRequestType();
}




 