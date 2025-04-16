package QuotingApplication.factories;

import org.springframework.stereotype.Component;
import QuotingApplication.pojos.Customer;

import java.util.List;

@Component
public interface PolicyFactory {

    default Customer getCustomerById(List<Customer> customers, int customerId) {
        return customers.stream().filter(a -> a.getId() == customerId).findFirst().orElse(null);
    }
}