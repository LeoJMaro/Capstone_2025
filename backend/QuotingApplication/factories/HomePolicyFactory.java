package QuotingApplication.factories;

import QuotingApplication.pojos.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HomePolicyFactory implements PolicyFactory{

    public static Policy createPolicy(Integer policyId, int customerId, java.util.Date startDate, java.util.Date endDate, double basePremium, double premium, String status, Dwelling dwelling) {
        return new HomePolicy(policyId, customerId, startDate, endDate, basePremium, premium, status, dwelling);
    }

    @Override
    public Customer getCustomerById(List<Customer> customers, int customerId) {
        return PolicyFactory.super.getCustomerById(customers, customerId);
    }

    /**
     * Calculates the insurance premium for the given home policy based on home characteristics,
     * location, heating type, and customer discounts.
     *
     * @param policy   The {@link HomePolicy} object representing the home policy to calculate.
     * @param customer The {@link Customer} object representing the customer who owns the policy.
     */
    public void calculateHomePolicy(HomePolicy policy, Customer customer, Dwelling dwelling) {

        double ageFactor = 1.0;
        double heatingFactor = 1.0;
        double locationFactor = 1.0;
        double additional = 0.0;

        // Calculate age factor
        if (dwelling.getAge() >= 25 && dwelling.getAge() < 50) {
            ageFactor = 1.25; // Increase for older homes.
        } else if (dwelling.getAge() > 50) {
            ageFactor = 1.50; // Higher increase for very old homes.
        }

        // Additional charge for high-value homes (over $250,000)
        if (dwelling.getHomeValue() > 250000.00) {
            additional = (dwelling.getHomeValue() - 250000.00) * 0.002;
        }

        // Set heating factor based on heating type
        if ("oil".equalsIgnoreCase(dwelling.getHeatingType())) {
            heatingFactor = 2.0;  // Higher risk for oil heating.
        } else if ("wood".equalsIgnoreCase(dwelling.getHeatingType())) {
            heatingFactor = 1.25;  // Moderate risk for wood heating.
        }

        // Set location factor if the home is in a rural area
        if ("rural".equalsIgnoreCase(dwelling.getLocation())) {
            locationFactor = 1.15;  // Slightly higher risk for rural locations.
        }

        // Final premium calculation based on all factors
        double premium = (policy.getBasePremium() + additional)
                * ageFactor
                * heatingFactor
                * locationFactor;

        // Set calculated values back to the policy object
        policy.setPremium(premium);
        policy.setAdditional(additional);
        policy.setAgeFactor(ageFactor);
        policy.setHeatingFactor(heatingFactor);
        policy.setLocationFactor(locationFactor);
    }

}
