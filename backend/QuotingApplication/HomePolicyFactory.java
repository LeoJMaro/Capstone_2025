package QuotingApplication;

import java.util.List;

public class HomePolicyFactory {

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
