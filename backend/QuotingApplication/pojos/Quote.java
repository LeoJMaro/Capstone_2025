package QuotingApplication.pojos;

import jakarta.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "quotes")
public class Quote {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quoteId;

    @ManyToOne
//    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    private String policyType;
    private double premium;

    @Temporal(TemporalType.DATE)
    private Date generatedDate;


    public Quote() {
    }

    public Quote(Customer customer, String policyType, double premium, Date generatedDate) {
        this.customer = customer;
        this.policyType = policyType;
        this.premium = premium;
        this.generatedDate = generatedDate;
    }

    public int getQuoteId() {
        return quoteId;
    }
    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public String getPolicyType() {
        return policyType;
    }
    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }
    public double getPremium() {
        return premium;
    }
    public void setPremium(double premium) {
        this.premium = premium;
    }
    public Date getGeneratedDate() {
        return generatedDate;
    }
    public void setGeneratedDate(Date generatedDate) {
        this.generatedDate = generatedDate;
    }
}
