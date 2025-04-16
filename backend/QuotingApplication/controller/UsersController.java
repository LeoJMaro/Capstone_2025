package QuotingApplication.controller;

import QuotingApplication.pojos.Users;
import QuotingApplication.dataaccess.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

@RestController
@RequestMapping(RESTNouns.VERSION_1 + RESTNouns.USER)
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

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
//        if (usersRepository.existsByEmail(user.getEmail())) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }

        // In a real application, you would hash the password here
        // user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));

        Users savedUser = usersRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Update user
    @PutMapping(RESTNouns.ID)
    public ResponseEntity<Users> updateUser(@PathVariable int id, @RequestBody Users user) {
        Optional<Users> existingUser = usersRepository.findById(id);
        if (existingUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//        // Check if username already exists for a different user
//        Optional<Users> userByUsername = usersRepository.findByUsername(user.getUsername());
//        if (userByUsername.isPresent() && userByUsername.get().getCustomerId() != id) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//
//        // Check if email already exists for a different user
//        Optional<Users> userByEmail = usersRepository.findByEmail(user.getEmail());
//        if (userByEmail.isPresent() && userByEmail.get().getCustomerId() != id) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//
//        // Preserve customer ID
//        int customerId = existingUser.get().getCustomerId();
//        user.setCustomerId(customerId);
//        user.setUserId(id);

        // In a real application, you would check if the password needs to be updated
        // if (!user.getPasswordHash().equals(existingUser.get().getPasswordHash())) {
        //     user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        // }

        Users updatedUser = usersRepository.save(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
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
}

