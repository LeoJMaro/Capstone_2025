package QuotingApplication.dataaccess;

import QuotingApplication.pojos.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    // CrudRepository provides basic CRUD operations
    // Integer is the type of the primary key (customerId)

    // Additional custom query methods if needed
    Customer findByEmail(String email);
}
