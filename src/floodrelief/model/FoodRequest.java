package floodrelief.model;

public class FoodRequest extends EmergencyReliefRequest {
    private int foodQuantity;
    public FoodRequest(int requestId,
                                  String requesterName,
                                  int peopleCount,
                                  int shelterId,
                                  String description,
                                  int urgencyLevel,
                                  int foodQuantity
                                ){
                                    super(requestId, requesterName, peopleCount, shelterId, description, urgencyLevel);
                                    if(foodQuantity <=0){
                                        throw new IllegalArgumentException("Food quantity must be positive");
                                    }
                                    this.foodQuantity = foodQuantity;
                                }

                  public int getFoodQuantity(){
                    return foodQuantity;
                  }    
                  @Override 
                  public String getRequestType(){
                    return "Food Requst";
                  }      
    
}
