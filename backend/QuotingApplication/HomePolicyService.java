package QuotingApplication;

public class HomePolicyService {
    public void calculateHomePolicy(HomePolicy policy) {
        double ageFactor = 1.0;
        double heatingFactor = 1.0;
        double locationFactor = 1.0;
        double additional = 0.0;

        // Calculate age factor
        if (policy.getHomeAge() >= 25 && policy.getHomeAge() < 50) {
            ageFactor = 1.25;
        } else if (policy.getHomeAge() > 50) {
            ageFactor = 1.50;
        }

        // Calculate additional charge for high-value homes
        if (policy.getHomeValue() > 250000.00) {
            additional = (policy.getHomeValue() - 250000.00) * 0.002;
        }

        // Calculate heating factor
        if ("oil".equalsIgnoreCase(policy.getHomeHeating())) {
            heatingFactor = 2.0;
        } else if ("wood".equalsIgnoreCase(policy.getHomeHeating())) {
            heatingFactor = 1.25;
        }

        // Calculate location factor
        if ("rural".equalsIgnoreCase(policy.getLocation())) {
            locationFactor = 1.15;
        }

        // Calculate final premium
        double premium = (policy.getBasePremium() + additional) * ageFactor * heatingFactor * locationFactor;
        policy.setPremium(premium);
        policy.setAdditional(additional);
        policy.setAgeFactor(ageFactor);
        policy.setHeatingFactor(heatingFactor);
        policy.setLocationFactor(locationFactor);
    }
}
