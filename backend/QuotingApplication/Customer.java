package QuotingApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Date dateOfBirth;
    private String passwordHash;
    private List <Object> policyList;

    public Customer(String firstName, String lastName, String email, String phone, String address, Date dateOfBirth, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.passwordHash = passwordHash;
        this.policyList = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<Object> getPolicyList() {
        return policyList;
    }

    public void setPolicyList(List<Object> policyList) {
        this.policyList = policyList;
    }
}
