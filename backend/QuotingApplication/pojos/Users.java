package pojos;

import jakarta.persistence.*;

@Entity
//@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @OneToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    private String username;
    private String passwordHash;
    private String role;

    public Users() {
    }

    public Users(Customer customer, String username, String passwordHash, String role) {
        this.customer = customer;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }


    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
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
