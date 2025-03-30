package QuotingApplication.factories;

import QuotingApplication.pojos.Customer;

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
public class CustomerFactory {

    /**
     * Calculates the age of a given customer based on their date of birth.
     *
     * @param customer The {@link Customer} whose age will be calculated.
     * @return The customer's age in years.
     * @throws IllegalArgumentException if the birth year is invalid (before 1900 or in the future).
     */
    public int getCustomerAge(Customer customer) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = customer.getDateOfBirth(); // Assuming this returns a LocalDate

        int birthYear = birthDate.getYear();
        if (birthYear < 1900 || birthYear > currentDate.getYear()) {
            throw new IllegalArgumentException("Invalid birth year: " + birthYear +
                    ". Birth year must be between 1900 and " + currentDate.getYear() + ".");
        }

        return Period.between(birthDate, currentDate).getYears();
    }

//    private String[] hashPassword(String password) {
//        try {
//            SecureRandom random = new SecureRandom();
//            byte[] salt = new byte[SALT_LENGTH];
//            random.nextBytes(salt);
//
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            md.update(salt);
//            byte[] hashedPassword = md.digest(password.getBytes());
//
//            String saltStr = Base64.getEncoder().encodeToString(salt);
//            String hashStr = Base64.getEncoder().encodeToString(hashedPassword);
//
//            return new String[]{saltStr, hashStr};
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException("Error hashing password", e);
//        }
//    }
}
