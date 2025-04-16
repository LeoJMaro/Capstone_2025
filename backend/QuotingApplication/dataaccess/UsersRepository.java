package dataaccess;

import pojos.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
    // Find user by username
    Optional<Users> findByUsername(String username);

    // Find user by email
    Optional<Users> findByEmail(String email);

    // Find users by role
    Iterable<Users> findByRole(String role);

    // Check if username exists
    boolean existsByUsername(String username);

    // Check if email exists
    boolean existsByEmail(String email);
}
