package QuotingApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The {@code Customer} class represents a customer in the quoting application.
 * <p>
 * It holds personal details, contact information, date of birth, and a list of insurance policies
 * associated with the customer. The policies may include auto and home policies.
 */
public class Customer {

    /**
     * The customer's first name.
     */
    private String firstName;

    /**
     * The customer's last name.
     */
    private String lastName;

    /**
     * The customer's email address.
     */
    private String email;

    /**
     * The customer's phone number.
     */
    private String phone;

    /**
     * The customer's physical address.
     */
    private String address;

    /**
     * The customer's date of birth.
     */
    private Date dateOfBirth;

    /**
     * The hashed version of the customer's password for account security.
     */
    private String passwordHash;

    /**
     * A list of the customer's policies, which may include auto and home policies.
     */
    private List<Object> policyList;

    /**
     * Constructs a new {@code Customer} object with the specified personal details and credentials.
     *
     * @param firstName    The customer's first name.
     * @param lastName     The customer's last name.
     * @param email        The customer's email address.
     * @param phone        The customer's phone number.
     * @param address      The customer's physical address.
     * @param dateOfBirth  The customer's date of birth.
     * @param passwordHash The hashed version of the customer's password.
     */
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

    /**
     * Returns the customer's first name.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the customer's first name.
     *
     * @param firstName The new first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the customer's last name.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the customer's last name.
     *
     * @param lastName The new last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the customer's email address.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the customer's email address.
     *
     * @param email The new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the customer's phone number.
     *
     * @return The phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the customer's phone number.
     *
     * @param phone The new phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the customer's physical address.
     *
     * @return The address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the customer's physical address.
     *
     * @param address The new address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the customer's date of birth.
     *
     * @return The date of birth.
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the customer's date of birth.
     *
     * @param dateOfBirth The new date of birth.
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the hashed version of the customer's password.
     *
     * @return The password hash.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the hashed version of the customer's password.
     *
     * @param passwordHash The new password hash.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Returns the list of policies associated with the customer.
     *
     * @return A list of policies.
     */
    public List<Object> getPolicyList() {
        return policyList;
    }

    /**
     * Sets the list of policies associated with the customer.
     *
     * @param policyList The new list of policies.
     */
    public void setPolicyList(List<Object> policyList) {
        this.policyList = policyList;
    }
}
