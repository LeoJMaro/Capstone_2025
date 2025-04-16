package QuotingApplication.dataaccess;
import QuotingApplication.pojos.Vehicle;
import org.springframework.stereotype.Repository;

/**
 * Since Vehicle is an @Embeddable class and not an @Entity,
 * it doesn't have its own repository that extends CrudRepository.
 * Operations on Vehicle objects should be done through the parent entity.
 *
 * This class is provided as a placeholder in case custom vehicle-related
 * repository operations are needed in the future.
 */
@Repository
public class VehicleRepository {
    // Custom methods for working with Vehicle objects would go here if needed
}
