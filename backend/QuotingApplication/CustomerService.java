package QuotingApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class CustomerService {
    private List<Customer> customers = new ArrayList<>();
    private static final int SALT_LENGTH = 16;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer registerCustomer(String firstName, String lastName, String email,
                                     String phone, String address, Date dateOfBirth,
                                     String password) {
        // Check if user already exists
        if (getCustomerByEmail(email) != null) {
            return null; // Customer already exists
        }

        // Generate salt and hash password
        String[] hashData = hashPassword(password);
        String saltAndHash = hashData[0] + ":" + hashData[1]; // Store salt:hash

        // Create new customer with hashed password
        Customer newCustomer = new Customer(firstName, lastName, email, phone,
                address, dateOfBirth, saltAndHash);
        addCustomer(newCustomer);
        return newCustomer;
    }

    public int getCustomerAge(Customer customer) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = customer.getDateOfBirth().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        int birthYear = birthDate.getYear();
        if (birthYear < 1900 || birthYear > currentDate.getYear()) {
            throw new IllegalArgumentException("Invalid birth year: " + birthYear +
                    ". Birth year must be between 1900 and " + currentDate.getYear() + ".");
        }
        return Period.between(birthDate, currentDate).getYears();
    }

    // Generate a new salt and hash the password
    private String[] hashPassword(String password) {
        try {
            // Generate a random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);

            // Hash the password with the salt
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());

            // Convert to base64 for storage
            String saltStr = Base64.getEncoder().encodeToString(salt);
            String hashStr = Base64.getEncoder().encodeToString(hashedPassword);

            return new String[] {saltStr, hashStr};
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Verify a password against a stored hash
    private boolean verifyPassword(String password, String storedHash) {
        try {
            // Split the stored value to get salt and hash
            String[] parts = storedHash.split(":");
            if (parts.length != 2) {
                return false;
            }

            String storedSalt = parts[0];
            String storedHashValue = parts[1];

            // Decode the salt
            byte[] salt = Base64.getDecoder().decode(storedSalt);

            // Hash the input password with the same salt
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            String hashStr = Base64.getEncoder().encodeToString(hashedPassword);

            // Compare the hashes
            return hashStr.equals(storedHashValue);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error verifying password", e);
        }
    }

    public boolean validatePassword(Customer customer, String password) {
        return verifyPassword(password, customer.getPasswordHash());
    }

    public boolean validateEmail(Customer customer, String email) {
        return customer.getEmail().equals(email);
    }

    public Customer getCustomerByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    public void addPolicy(Customer customer, Object policy) {
        customer.getPolicyList().add(policy);
    }
}