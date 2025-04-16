package factories;

import org.springframework.stereotype.Component;
import pojos.Policy;
import pojos.Customer;
import pojos.HomePolicy;
import pojos.AutoPolicy;
import pojos.Vehicle;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Component
public class AutoPolicyFactory implements PolicyFactory {

    public static Policy createPolicy(int policyId, int customerId, java.util.Date startDate, java.util.Date endDate, double basePremium, double premium, String policyType, String status) {
        return new AutoPolicy(policyId, customerId, startDate, endDate, basePremium, premium, policyType, status);
    }

    @Override
    public Customer getCustomerById(List<Customer> customers, int customerId) {
        return PolicyFactory.super.getCustomerById(customers, customerId);
    }

    public void calculateAutoPolicy(AutoPolicy policy, int customerId, Vehicle vehicle, List<Customer> customers) {
        double ageFactor = 1.0;
        double accidentFactor = 1.0;
        double vehicleFactor = 1.0;

        Customer customer = getCustomerById(customers, customerId);

        // Driver Age Factor
        if (Period.between(customer.getDateOfBirth(), LocalDate.now()).getYears() < 25) {
            ageFactor = 2.0;
        }

        // Accident Factor
        int accidents = vehicle.getVehicleAccidents();
        if (accidents > 1) {
            accidentFactor = 2.5;
        } else if (accidents == 1) {
            accidentFactor = 1.25;
        }

        int currentYear = LocalDate.now().getYear();
        int vehicleAge = currentYear - vehicle.getVehicleYear();

        // Vehicle Factor
        if (vehicleAge > 10) {
            vehicleFactor = 2.0;
        } else if (vehicleAge > 5) {
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
