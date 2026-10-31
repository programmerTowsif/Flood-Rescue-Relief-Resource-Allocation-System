package floodrelief.model;

public class WaterSupply extends ReliefResource{
    private String unit;
    public WaterSupply (int resourceId, String resourceName,int quantity,String unit){
        super(resourceId, resourceName, quantity);
        if(unit == null || unit.trim().isEmpty()){
            throw new IllegalArgumentException("unit cannot be empty");
        }
        this unit = unit;

    }
    public String getUnit(){
        return unit;
    }
    @Override 
    public String getResourceType(){
        return "Water supply";
    }
}