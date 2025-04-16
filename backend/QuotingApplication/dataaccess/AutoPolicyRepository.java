package dataaccess;

import org.springframework.data.repository.CrudRepository;
import pojos.AutoPolicy;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AutoPolicyRepository extends CrudRepository<AutoPolicy, Integer> {
    // Find auto policies for a specific customer
    List<AutoPolicy> findByCustomerId(int customerId);

    // Find auto policies by status
    List<AutoPolicy> findByStatus(String status);
}