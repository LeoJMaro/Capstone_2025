package QuotingApplication.pojos;

import jakarta.persistence.*; // CHANGED: Use Jakarta Persistence annotations
import java.util.Date;

@Entity
public class AutoPolicy extends Policy {

    private double ageFactor;
    private double accidentFactor;
    private double vehicleFactor;


    public AutoPolicy() {
    }

    public AutoPolicy(int policyId, int customerId, Date startDate, Date endDate,
                      double basePremium, double premium, String policyType, String status) {
        super(policyId, customerId, startDate, endDate, basePremium, premium, policyType, status);
    }

    public double getAgeFactor() {
        return ageFactor;
    }
    public void setAgeFactor(double ageFactor) {
        this.ageFactor = ageFactor;
    }
    public double getAccidentFactor() {
        return accidentFactor;
    }
    public void setAccidentFactor(double accidentFactor) {
        this.accidentFactor = accidentFactor;
    }
    public double getVehicleFactor() {
        return vehicleFactor;
    }
    public void setVehicleFactor(double vehicleFactor) {
        this.vehicleFactor = vehicleFactor;
    }
}
