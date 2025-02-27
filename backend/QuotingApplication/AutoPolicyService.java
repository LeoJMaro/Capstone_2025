package QuotingApplication;

import java.util.List;

/**
 * The {@code AutoPolicyService} class provides services to calculate the premium for
 * an {@code AutoPolicy} based on various factors, including driver age, accident history,
 * vehicle age, and whether the customer holds both auto and home policies.
 */
public class AutoPolicyService {

    /**
     * Calculates the premium for a given {@link AutoPolicy} based on the associated customer's profile
     * and policy history.
     * <p>
     * The final premium is calculated using:
     * <ul>
     *     <li>Driver age factor</li>
     *     <li>Accident history factor</li>
     *     <li>Vehicle age factor</li>
     *     <li>A discount factor if the customer holds both auto and home policies</li>
     * </ul>
     * The calculated premium and contributing factors are set directly on the {@code policy}.
     *
     * @param policy   The {@link AutoPolicy} whose premium will be calculated.
     * @param customer The {@link Customer} associated with the policy, whose policies determine eligibility for discounts.
     */
    public void calculateAutoPolicy(AutoPolicy policy, Customer customer) {
        boolean hasBothPolicies = customerHasBothPolicies(customer);

        double ageFactor = 1.0;
        double accidentFactor = 1.0;
        double vehicleFactor = 1.0;
        double customerDiscountFactor = hasBothPolicies ? 0.9 : 1.0;

        // Driver Age Factor
        if (policy.getDriverAge() < 25) {
            ageFactor = 2.0;
        }

        // Accident Factor
        int accidents = policy.getAccidentCount();
        if (accidents > 1) {
            accidentFactor = 2.5;
        } else if (accidents == 1) {
            accidentFactor = 1.25;
        }

        // Vehicle Factor
        int vehicleAge = policy.getVehicleAge();
        if (vehicleAge > 10) {
            vehicleFactor = 2.0;
        } else if (vehicleAge > 5 && vehicleAge < 10) {
            vehicleFactor = 1.5;
        }

        // Calculate Final Premium
        double premium = policy.getBasePremium() * ageFactor * accidentFactor * vehicleFactor * customerDiscountFactor;

        // Set calculated values
        policy.setPremium(premium);
        policy.setAgeFactor(ageFactor);
        policy.setAccidentFactor(accidentFactor);
        policy.setVehicleFactor(vehicleFactor);
    }

    /**
     * Determines if a customer holds both an auto policy and a home policy.
     * This is used to determine if the customer is eligible for a discount on their auto policy.
     *
     * @param customer The {@link Customer} whose policies will be checked.
     * @return {@code true} if the customer has both an auto and a home policy; {@code false} otherwise.
     */
    private boolean customerHasBothPolicies(Customer customer) {
        List<Object> policies = customer.getPolicyList();
        boolean hasAuto = false, hasHome = false;

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
