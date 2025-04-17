package QuotingApplication.controller;

import QuotingApplication.dataaccess.UsersRepository;
import QuotingApplication.factories.CustomerFactory;
import QuotingApplication.pojos.Customer;
import QuotingApplication.dataaccess.CustomerRepository;
import QuotingApplication.pojos.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(RESTNouns.VERSION_1 + RESTNouns.CUSTOMER)
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerFactory customerFactory;

    @Autowired
    private UsersRepository usersRepository;

    // Get all customers
    @GetMapping
    public ResponseEntity<Iterable<Customer>> getAllCustomers() {
        try {
            Iterable<Customer> customers = customerRepository.findAll();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error retrieving all customers: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get customer by ID
    @GetMapping(RESTNouns.ID)
    public ResponseEntity<Customer> getCustomerById(@PathVariable (name = "id") int id) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            System.err.println("Error retrieving customer by ID: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create new customer (accepting JSON instead of form params)
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            customer.setId(null); // Ensure it's treated as a new entity
            Customer savedCustomer = customerRepository.save(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error creating customer: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Update customer
    @PutMapping(RESTNouns.ID)
    public ResponseEntity<Customer> updateCustomer(@PathVariable(name = "id") int id, @RequestBody Customer customer) {
        try {
            if (!customerRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            customer.setId(id);
            Customer updatedCustomer = customerRepository.save(customer);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating customer: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete customer
    @DeleteMapping(RESTNouns.ID)
    public ResponseEntity<Void> deleteCustomer(@PathVariable(name = "id") int id) {
        try {
            if (!customerRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            customerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.err.println("Error deleting customer: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get customer age
    @GetMapping(RESTNouns.ID + "/age")
    public ResponseEntity<Integer> getCustomerAge(@PathVariable(name = "id") int id) {
        try {
            Optional<Customer> customerOpt = customerRepository.findById(id);
            if (customerOpt.isPresent()) {
                int age = customerFactory.getCustomerAge(customerOpt.get());
                return new ResponseEntity<>(age, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.err.println("Error calculating customer age: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Find customer by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable(name = "email") String email) {
        try {
            Customer customer = customerRepository.findByEmail(email);
            if (customer != null) {
                return new ResponseEntity<>(customer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving customer by email: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id-by-email/{email}")
    public ResponseEntity<Integer> getCustomerIdByEmail(@PathVariable(name = "email") String email) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByEmail(email));
        return customer.map(value -> new ResponseEntity<>(value.getId(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
