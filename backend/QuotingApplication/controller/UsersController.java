package QuotingApplication.controller;

import QuotingApplication.pojos.Users;
import QuotingApplication.dataaccess.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(RESTNouns.VERSION_1 + RESTNouns.USER)
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Get all users
    @GetMapping
    public Iterable<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // Get user by ID
    @GetMapping(RESTNouns.ID)
    public ResponseEntity<Users> getUserById(@PathVariable int id) {
        Optional<Users> user = usersRepository.findById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create new user
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        // Check if username already exists
        if (usersRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        // Check if email already exists
        if (usersRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        // Hash the password before saving
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));

        Users savedUser = usersRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Update user
    @PutMapping(RESTNouns.ID)
    public ResponseEntity<Users> updateUser(@PathVariable(name = "id") int id, @RequestBody Users user) {
        Optional<Users> existingUser = usersRepository.findById(id);
        if (existingUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Check if username already exists for a different user
        Optional<Users> userByUsername = usersRepository.findByUsername(user.getUsername());
        if (userByUsername.isPresent() && userByUsername.get().getId() != id) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        // Check if email already exists for a different user
        Optional<Users> userByEmail = usersRepository.findByEmail(user.getEmail());
        if (userByEmail.isPresent() && userByEmail.get().getId() != id) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        // Only hash the password if it has changed
        if (!user.getPasswordHash().equals(existingUser.get().getPasswordHash())) {
            user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        }

        // Preserve the customer relationship
        user.setCustomer(existingUser.get().getCustomer());
        user.setId(id);  // Make sure to set the ID

        Users updatedUser = usersRepository.save(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Endpoint for password validation
    @PostMapping("/validate-password")
    public ResponseEntity<Boolean> validatePassword(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("passwordHash");

        Optional<Users> user = usersRepository.findByUsername(username);
        if (user.isPresent()) {
            boolean valid = passwordEncoder.matches(password, user.get().getPasswordHash());
            return new ResponseEntity<>(valid, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    // Delete user
    @DeleteMapping(RESTNouns.ID)
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (!usersRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usersRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get users by role
    @GetMapping("/role/{role}")
    public ResponseEntity<Iterable<Users>> getUsersByRole(@PathVariable String role) {
        Iterable<Users> users = usersRepository.findByRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Check if username exists
    @GetMapping("/check-username/{username}")
    public ResponseEntity<Boolean> checkUsernameExists(@PathVariable String username) {
        boolean exists = usersRepository.existsByUsername(username);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // Check if email exists
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        boolean exists = usersRepository.existsByEmail(email);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("/id-by-email/{email}")
    public ResponseEntity<Integer> getUserIdByEmail(@PathVariable(name="email") String email) {
        Optional<Users> user = usersRepository.findByEmail(email);
        return user.map(value -> new ResponseEntity<>(value.getId(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

