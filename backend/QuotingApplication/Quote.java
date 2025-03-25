package QuotingApplication;

import java.util.Date;

public class Quote extends Customer {
    private int quoteId;
    private Object policyType;
    private double premium;
    private Date generatedDate;

    public Quote(int customerId, String firstName, String lastName, String email, String phone, String address, String dateOfBirth, String createdAt, int quoteId, Object policyType, double premium, Date generatedDate) {
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

    public Object getPolicyType() {
        return policyType;
    }

    public void setPolicyType(Object policyType) {
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
