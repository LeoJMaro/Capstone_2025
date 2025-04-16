package dataaccess;

import pojos.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Integer> {
    // Find quotes by policy type
    List<Quote> findByPolicyType(String policyType);

    // Find quotes generated after a specific date
    List<Quote> findByGeneratedDateAfter(Date date);

    // Find quotes for a customer by their ID
    // Note: Since Quote extends Customer, we need to use the customer ID
    List<Quote> findByCustomerId(int customerId);
}
