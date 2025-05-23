package QuotingApplication.dataaccess;


import QuotingApplication.pojos.Policy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends CrudRepository<Policy, Integer> {
    // Find all policies for a specific customer
    List<Policy> findByCustomerId(int customerId);


    // Find policies by status
    List<Policy> findByStatus(String status);
}
