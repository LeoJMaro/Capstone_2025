package QuotingApplication.pojos;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "policy_type")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer policyId;

    private int customerId;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private double basePremium;
    private double premium;
    private String status;

    public Policy() {
    }

    public Policy(Integer policyId, int customerId, Date startDate, Date endDate,
                  double basePremium, double premium, String status) {
        this.policyId = policyId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.basePremium = basePremium;
        this.premium = premium;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
