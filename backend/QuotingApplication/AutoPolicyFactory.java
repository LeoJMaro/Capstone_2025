package QuotingApplication;

import java.util.List;

public class AutoPolicyFactory {


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
