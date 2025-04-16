package QuotingApplication.pojos;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    // No auto-generation needed if you are using @MapsId (if that's your strategy)
    private int id;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @MapsId
    private Customer customer;

    private String email;  // <-- New field added
    private String username;
    private String passwordHash;
    private String role;

    public Users() {
    }

    public Users(Customer customer, String email, String username, String passwordHash, String role) {
        this.customer = customer;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
