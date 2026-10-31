package floodrelief.model;
import floodrelief.interfaces.Prioritizable;
public class EmergencyReliefRequest extends ReliefRequest
        implements Prioritizable {
    private int urgencyLevel;
    public EmergencyReliefRequest(int requestId,String requesterName,int peopleCount,int shelterId,String description,int urgencyLevel) {
        super(requestId, requesterName, peopleCount,shelterId, description);
        if (urgencyLevel < 1 || urgencyLevel > 5) {
            throw new IllegalArgumentException(
                "Urgency level must be between 1 and 5."
            );
        }
        this.urgencyLevel = urgencyLevel;
    }
    public int getUrgencyLevel() {
        return urgencyLevel;
    }
    @Override
    public int calculatePriority() {
        return urgencyLevel * 20;
    }
    @Override
    public String getRequestType() {
        return "Emergency Relief";
    }
}