package QuotingApplication.factories;

import QuotingApplication.pojos.Customer;
import QuotingApplication.pojos.Policy;
import java.util.Date;
import java.util.List;

public interface PolicyFactory {

    default Customer getCustomerById(List<Customer> customers, int customerId) {
        return customers.stream().filter(a -> a.getCustomerId() == customerId).findFirst().orElse(null);
    }
}