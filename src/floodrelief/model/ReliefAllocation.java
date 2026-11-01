package floodrelief.model;

public class ReliefAllocation {
    private int requestId;
    private  int resourceId;
    private int allocatedQuantity;
    private String allocationDate;
    public ReliefAllocation(int requestId,int resourceId,int allocatedQuantity, String allocationDate){
        if(requestId <= 0){
            throw new IllegalArgumentException("Request id must be positive");
        }
         if(resourceId <= 0){
            throw new IllegalArgumentException("resourceId id must be positive");
        }
           if (allocatedQuantity <= 0) {
            throw new IllegalArgumentException("Allocated quantity must be positive.");}

            if (allocationDate == null || allocationDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Allocation date cannot be empty.");
        }
          this.requestId = requestId;
        this.resourceId = resourceId;
        this.allocatedQuantity = allocatedQuantity;
        this.allocationDate = allocationDate;    
    }
      public int getRequestId() {
        return requestId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public int getAllocatedQuantity() {
        return allocatedQuantity;
    }

    public String getAllocationDate() {
        return allocationDate;
    }
}
