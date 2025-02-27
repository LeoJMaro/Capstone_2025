package QuotingApplication;

import java.util.List;

/**
 * Service class responsible for calculating the insurance premium for a home policy.
 * This class applies various factors such as age, heating type, location, and discounts
 * based on customer eligibility.
 */
public class HomePolicyService {

    /**
     * Calculates the insurance premium for the given home policy based on home characteristics,
     * location, heating type, and customer discounts.
     *
     * @param policy   The {@link HomePolicy} object representing the home policy to calculate.
     * @param customer The {@link Customer} object representing the customer who owns the policy.
     */
    public void calculateHomePolicy(HomePolicy policy, Customer customer) {
        boolean hasBothPolicies = customerHasBothPolicies(customer);

        double ageFactor = 1.0;
        double heatingFactor = 1.0;
        double locationFactor = 1.0;
        double additional = 0.0;
        double customerDiscountFactor = hasBothPolicies ? 0.9 : 1.0;  // 10% discount if both home and auto policies are held.

        // Calculate age factor
        if (policy.getHomeAge() >= 25 && policy.getHomeAge() < 50) {
            ageFactor = 1.25; // Increase for older homes.
        } else if (policy.getHomeAge() > 50) {
            ageFactor = 1.50; // Higher increase for very old homes.
        }

        // Additional charge for high-value homes (over $250,000)
        if (policy.getHomeValue() > 250000.00) {
            additional = (policy.getHomeValue() - 250000.00) * 0.002;
        }

        // Set heating factor based on heating type
        if ("oil".equalsIgnoreCase(policy.getHomeHeating())) {
            heatingFactor = 2.0;  // Higher risk for oil heating.
        } else if ("wood".equalsIgnoreCase(policy.getHomeHeating())) {
            heatingFactor = 1.25;  // Moderate risk for wood heating.
        }

        // Set location factor if the home is in a rural area
        if ("rural".equalsIgnoreCase(policy.getLocation())) {
            locationFactor = 1.15;  // Slightly higher risk for rural locations.
        }

        // Final premium calculation based on all factors
        double premium = (policy.getBasePremium() + additional)
                * ageFactor
                * heatingFactor
                * locationFactor
                * customerDiscountFactor;

        // Set calculated values back to the policy object
        policy.setPremium(premium);
        policy.setAdditional(additional);
        policy.setAgeFactor(ageFactor);
        policy.setHeatingFactor(heatingFactor);
        policy.setLocationFactor(locationFactor);
    }

    /**
     * Checks whether the given customer holds both an auto policy and a home policy.
     * Customers with both types of policies are eligible for a discount.
     *
     * @param customer The {@link Customer} whose policies are being checked.
     * @return {@code true} if the customer has both an auto policy and a home policy, {@code false} otherwise.
     */
    private boolean customerHasBothPolicies(Customer customer) {
        List<Object> policies = customer.getPolicyList();
        boolean hasAuto = false;
        boolean hasHome = false;

        for (Object policy : policies) {
            if (policy instanceof AutoPolicy) {
                hasAuto = true;
            } else if (policy instanceof HomePolicy) {
                hasHome = true;
            }
        }

        return hasAuto && hasHome;
    }
}
