package QuotingApplication;

import java.util.Date;

public class Policy {
    private int policyId;
    private int customerId;
    enum policyType {AutoPolicy, HomePolicy};
    private Date startDate;
    private Date endDate;
    private double basePremium;
    private double premium;
    enum status {Active, Inactive};
    private policyType policyType;
    private status status;

    public Policy(int policyId, int customerId, Date startDate, Date endDate, double basePremium, double premium, Policy.policyType policyType, Policy.status status) {
        this.policyId = policyId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.basePremium = basePremium;
        this.premium = premium;
        this.policyType = policyType;
        this.status = status;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getBasePremium() {
        return basePremium;
    }

    public void setBasePremium(double basePremium) {
        this.basePremium = basePremium;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public policyType getPolicyType() {
        return policyType;
    }

    public void setPolicyType(policyType policyType) {
        this.policyType = policyType;
    }

    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }
}
