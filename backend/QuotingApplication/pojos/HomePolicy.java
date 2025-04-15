package QuotingApplication.pojos;

import jakarta.persistence.*;
import java.util.Date;


@Entity
public class HomePolicy extends Policy {


    @Embedded
    private Dwelling dwelling;

    private double additional = 0;
    private double ageFactor = 0;
    private double locationFactor = 0;
    private double heatingFactor = 0;

    /**
     * CHANGED: Added no-arg constructor required by JPA.
     */
    public HomePolicy() {
        super();
    }

    public HomePolicy(int policyId, int customerId, Date startDate, Date endDate,
                      double basePremium, double premium, String policyType, String status, Dwelling dwelling) {
        super(policyId, customerId, startDate, endDate, basePremium, premium, policyType, status);
        this.dwelling = dwelling;
    }

    // Getters and setters
    public Dwelling getDwelling() {
        return dwelling;
    }
    public void setDwelling(Dwelling dwelling) {
        this.dwelling = dwelling;
    }
    public double getAdditional() {
        return additional;
    }
    public void setAdditional(double additional) {
        this.additional = additional;
    }
    public double getAgeFactor() {
        return ageFactor;
    }
    public void setAgeFactor(double ageFactor) {
        this.ageFactor = ageFactor;
    }
    public double getLocationFactor() {
        return locationFactor;
    }
    public void setLocationFactor(double locationFactor) {
        this.locationFactor = locationFactor;
    }
    public double getHeatingFactor() {
        return heatingFactor;
    }
    public void setHeatingFactor(double heatingFactor) {
        this.heatingFactor = heatingFactor;
    }
}
