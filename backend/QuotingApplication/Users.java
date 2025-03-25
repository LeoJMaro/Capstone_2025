package QuotingApplication;

public class Users extends Customer{
    private int userId;
    private String username;
    private String passwordHash;
    private String role;

    public Users(int customerId, String firstName, String lastName, String email, String phone, String address, String dateOfBirth, String createdAt, int userId, String username, String passwordHash, String role) {
        super(customerId, firstName, lastName, email, phone, address, dateOfBirth, createdAt);

        this.userId = userId;
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
