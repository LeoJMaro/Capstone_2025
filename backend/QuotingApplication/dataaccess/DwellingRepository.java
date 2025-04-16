package dataaccess;

import pojos.Dwelling;
import org.springframework.stereotype.Repository;

/**
 * since Dwelling is an @Embeddable class and not an @Entity,
 * it doesn't have its own repository that extends CrudRepository.
 * Operations on Dwelling objects should be done through the parent entity.
 *
 * This class is provided as a placeholder in case custom dwelling-related
 * repository operations are needed in the future.
 */
@Repository
public class DwellingRepository {
    // Custom methods for working with Dwelling objects would go here if needed
}
