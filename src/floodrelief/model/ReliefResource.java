package floodrelief.model;

public abstract class ReliefResource{
    private int resourceId;
    private String resourceName;
    private int quantity;

    public ReliefResource(int resourceId, String resourceName, int quantity){
        if(resourceId <= 0){
            throw new IllegalArgumentException("resource id must be positve");
        }
        if(resourceName == null || resourceName.trim().isEmpty()){
            throw new IllegalArgumentException("resource name does not exits empty or null");
        }
        if(quantity < 0){
            throw new IllegalArgumentException("quantity cannot be negative.");
        }

        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.quantity = quantity;
    }

    public int getResourceId(){
        return resourceId;
    }
    public String getResourceName(){
         return resourceName;
    }
    public int getQuantity(){
          return quantity;
    }

    public void addQuantity(int amount){

        if(amount <= 0){
            throw new IllegalArgumentException("amount must be positive");
        }
        quantity+=amount;
    }
    public void reduceQuantity(int amount){

        if(amount <= 0){
            throw new IllegalArgumentException("amount must be positive");
        }
        if(amount > quantity){
            throw new IllegalArgumentException("Insufficient resource quantity");
        }
        quantity-=amount;
    }
    public abstract String getResourceType();
}