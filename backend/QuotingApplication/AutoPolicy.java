package QuotingApplication;

import java.util.Date;

public class AutoPolicy extends Policy {
    private Customer customer;
    private double ageFactor;
    private double accidentFactor;
    private double vehicleFactor;

    public AutoPolicy(int policyId, int customerId, Date startDate, Date endDate, double basePremium, double premium, Policy.policyType policyType, Policy.status status, Customer customer) {
        super(policyId, customerId, startDate, endDate, basePremium, premium, policyType, status);
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
