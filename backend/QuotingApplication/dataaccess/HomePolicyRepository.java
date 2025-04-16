package QuotingApplication.dataaccess;
import QuotingApplication.pojos.HomePolicy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomePolicyRepository extends CrudRepository<HomePolicy, Integer> {
    // Find home policies for a specific customer
    List<HomePolicy> findByCustomerId(int customerId);

    // Find home policies by status
    List<HomePolicy> findByStatus(String status);
}
