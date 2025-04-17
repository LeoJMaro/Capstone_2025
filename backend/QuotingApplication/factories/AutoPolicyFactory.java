package QuotingApplication.factories;

import QuotingApplication.factories.PolicyFactory;
import org.springframework.stereotype.Component;
import QuotingApplication.pojos.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
@Component
public class AutoPolicyFactory implements QuotingApplication.factories.PolicyFactory {

    public static Policy createPolicy(Integer policyId, int customerId, java.util.Date startDate, java.util.Date endDate, double basePremium, double premium, String status) {
        return new AutoPolicy(policyId, customerId, startDate, endDate, basePremium, premium, status);
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

        // Calculate base premium before tax
        double premiumBeforeTax = policy.getBasePremium() * ageFactor * accidentFactor * vehicleFactor;

        // Apply tax
        double taxRate = 0.15; // 15% tax
        double taxAmount = premiumBeforeTax * taxRate;
        double finalPremium = premiumBeforeTax + taxAmount;

        // Set calculated values
        policy.setPremium(finalPremium);
        policy.setAgeFactor(ageFactor);
        policy.setAccidentFactor(accidentFactor);
        policy.setVehicleFactor(vehicleFactor);
    }

}
