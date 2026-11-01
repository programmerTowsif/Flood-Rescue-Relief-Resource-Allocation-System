
package floodrelief.model;

public class FoodSupply extends ReliefResource {

    private String expiryDate;

    public FoodSupply(int resourceId, String resourceName,
                      int quantity, String expiryDate) {

        super(resourceId, resourceName, quantity);

        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Expiry date cannot be empty."
            );
        }

        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String getResourceType() {
        return "Food Supply";
    }
}
