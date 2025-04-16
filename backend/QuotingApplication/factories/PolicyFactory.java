package factories;

import org.springframework.stereotype.Component;
import pojos.Customer;
import pojos.Policy;
import java.util.Date;
import java.util.List;

@Component
public interface PolicyFactory {

    default Customer getCustomerById(List<Customer> customers, int customerId) {
        return customers.stream().filter(a -> a.getCustomerId() == customerId).findFirst().orElse(null);
    }
}