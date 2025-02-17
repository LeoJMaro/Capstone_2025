package QuotingApplication;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }


    public int getCustomerAge(Customer customer) {
        LocalDate currentDate = LocalDate.now();

        // Convert java.util.Date to java.time.LocalDate
        LocalDate birthDate = customer.getDateOfBirth().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return Period.between(birthDate, currentDate).getYears();
    }


    public boolean validatePassword(Customer customer, String password) {
        return customer.getPasswordHash().equals(password);
    }

    public boolean validateEmail(Customer customer, String email) {
        return customer.getEmail().equals(email);
    }

    public Customer getCustomerByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    public void addPolicy(Customer customer, Object policy) {
        customer.getPolicyList().add(policy);
    }

}
