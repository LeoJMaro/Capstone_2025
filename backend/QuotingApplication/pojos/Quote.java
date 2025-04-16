package pojos;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Quote extends Customer {

    private int quoteId;
    private String policyType;
    private double premium;

    @Temporal(TemporalType.DATE)
    private Date generatedDate;

    public Quote() {
        super();
    }

    public Quote(int customerId, String firstName, String lastName, String email, String phone, String address,
                 java.time.LocalDate dateOfBirth, String createdAt, int quoteId, String policyType,
                 double premium, Date generatedDate) {
        super(customerId, firstName, lastName, email, phone, address, dateOfBirth, createdAt);
        this.quoteId = quoteId;
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
