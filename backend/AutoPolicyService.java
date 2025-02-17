package QuotingApplication;

public class AutoPolicyService {
    public void calculateAutoPolicy(AutoPolicy policy) {
        double ageFactor = 1.0;
        double accidentFactor = 1.0;
        double vehicleFactor = 1.0;

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
        double premium = policy.getBasePremium() * ageFactor * accidentFactor * vehicleFactor;

        // Set calculated values
        policy.setPremium(premium);
        policy.setAgeFactor(ageFactor);
        policy.setAccidentFactor(accidentFactor);
        policy.setVehicleFactor(vehicleFactor);
    }
}
