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

/**
 * Service class responsible for managing {@link Customer} objects within the Quoting Application.
 * <p>
 * Provides functionality for customer registration, password management (hashing and verification),
 * retrieving customer information, managing policies, and age calculation.
 */
public class CustomerService {

    /**
     * List of registered customers.
     */
    private List<Customer> customers = new ArrayList<>();

    /**
     * Length of the generated salt used for password hashing.
     */
    private static final int SALT_LENGTH = 16;

    /**
     * Retrieves the list of all registered customers.
     *
     * @return A list of {@link Customer} objects.
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Adds a new customer to the list of registered customers.
     *
     * @param customer The {@link Customer} to add.
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Registers a new customer by creating a new {@link Customer} object and hashing their password.
     * If a customer with the given email already exists, this method returns {@code null}.
     *
     * @param firstName   The customer's first name.
     * @param lastName    The customer's last name.
     * @param email       The customer's email address.
     * @param phone       The customer's phone number.
     * @param address     The customer's address.
     * @param dateOfBirth The customer's date of birth.
     * @param password    The customer's plaintext password.
     * @return The newly registered {@link Customer} object, or {@code null} if the customer already exists.
     */
    public Customer registerCustomer(String firstName, String lastName, String email,
                                     String phone, String address, Date dateOfBirth,
                                     String password) {
        if (getCustomerByEmail(email) != null) {
            return null; // Customer already exists
        }

        String[] hashData = hashPassword(password);
        String saltAndHash = hashData[0] + ":" + hashData[1];

        Customer newCustomer = new Customer(firstName, lastName, email, phone,
                address, dateOfBirth, saltAndHash);
        addCustomer(newCustomer);
        return newCustomer;
    }

    /**
     * Calculates the age of a given customer based on their date of birth.
     *
     * @param customer The {@link Customer} whose age will be calculated.
     * @return The customer's age in years.
     * @throws IllegalArgumentException if the birth year is invalid (before 1900 or in the future).
     */
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

    /**
     * Generates a salt and hashes a given password using SHA-256.
     *
     * @param password The plaintext password to hash.
     * @return An array containing the salt and hash, both Base64-encoded.
     */
    private String[] hashPassword(String password) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());

            String saltStr = Base64.getEncoder().encodeToString(salt);
            String hashStr = Base64.getEncoder().encodeToString(hashedPassword);

            return new String[]{saltStr, hashStr};
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Verifies whether a given plaintext password matches a stored hashed password.
     *
     * @param password   The plaintext password to verify.
     * @param storedHash The stored hash in "salt:hash" format.
     * @return {@code true} if the password is correct, {@code false} otherwise.
     */
    private boolean verifyPassword(String password, String storedHash) {
        try {
            String[] parts = storedHash.split(":");
            if (parts.length != 2) {
                return false;
            }

            String storedSalt = parts[0];
            String storedHashValue = parts[1];

            byte[] salt = Base64.getDecoder().decode(storedSalt);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            String hashStr = Base64.getEncoder().encodeToString(hashedPassword);

            return hashStr.equals(storedHashValue);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error verifying password", e);
        }
    }

    /**
     * Validates a customer's password by comparing the given plaintext password with the stored hash.
     *
     * @param customer The {@link Customer} whose password will be validated.
     * @param password The plaintext password to check.
     * @return {@code true} if the password is correct, {@code false} otherwise.
     */
    public boolean validatePassword(Customer customer, String password) {
        return verifyPassword(password, customer.getPasswordHash());
    }

    /**
     * Validates a customer's email address.
     *
     * @param customer The {@link Customer} whose email will be validated.
     * @param email    The email to check.
     * @return {@code true} if the email matches the customer's stored email, {@code false} otherwise.
     */
    public boolean validateEmail(Customer customer, String email) {
        return customer.getEmail().equals(email);
    }

    /**
     * Retrieves a customer by their email address.
     *
     * @param email The email address to search for.
     * @return The corresponding {@link Customer}, or {@code null} if no customer is found.
     */
    public Customer getCustomerByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Adds an insurance policy to a customer's list of policies.
     *
     * @param customer The {@link Customer} to whom the policy will be added.
     * @param policy   The policy to add (can be an {@code AutoPolicy} or {@code HomePolicy}).
     */
    public void addPolicy(Customer customer, Object policy) {
        customer.getPolicyList().add(policy);
    }
}
