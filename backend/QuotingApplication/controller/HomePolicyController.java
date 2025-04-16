package QuotingApplication.controller;

import QuotingApplication.factories.HomePolicyFactory;
import QuotingApplication.pojos.Customer;
import QuotingApplication.pojos.Dwelling;
import QuotingApplication.pojos.HomePolicy;
import QuotingApplication.dataaccess.CustomerRepository;
import QuotingApplication.dataaccess.HomePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(RESTNouns.VERSION_1 + RESTNouns.HOME_POLICY)
public class HomePolicyController {

    @Autowired
    private HomePolicyRepository homePolicyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HomePolicyFactory homePolicyFactory;

    // Get all home policies
    @GetMapping
    public Iterable<HomePolicy> getAllHomePolicies() {
        return homePolicyRepository.findAll();
    }

    // Get home policy by ID
    @GetMapping(RESTNouns.ID)
    public ResponseEntity<HomePolicy> getHomePolicyById(@PathVariable int id) {
        Optional<HomePolicy> policy = homePolicyRepository.findById(id);
        return policy.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create new home policy
    @PostMapping
    public ResponseEntity<HomePolicy> createHomePolicy(@RequestBody HomePolicy policy) {
        // Save the policy first
        HomePolicy savedPolicy = homePolicyRepository.save(policy);

        // Assuming you have customer and dwelling information from the request
        // You might need to fetch these objects from your database or request body if they are not part of the HomePolicy object.
        Optional<Customer> customerOpt = customerRepository.findById(policy.getCustomerId());
        if (customerOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Customer not found
        }
        Customer customer = customerOpt.get();

        // You can assume the dwelling information is part of the HomePolicy object.
        Dwelling dwelling = policy.getDwelling();

        // Now call the calculation method using the homePolicyFactory
        homePolicyFactory.calculateHomePolicy(policy, customer, dwelling);

        // Save the policy again after calculation if needed
        HomePolicy updatedPolicy = homePolicyRepository.save(policy);

        // Return the updated policy with the calculated premium
        return new ResponseEntity<>(updatedPolicy, HttpStatus.CREATED);
    }


    // Update home policy
    @PutMapping(RESTNouns.ID)
    public ResponseEntity<HomePolicy> updateHomePolicy(@PathVariable int id, @RequestBody HomePolicy policy) {
        if (!homePolicyRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        policy.setPolicyId(id);
        HomePolicy updatedPolicy = homePolicyRepository.save(policy);
        return new ResponseEntity<>(updatedPolicy, HttpStatus.OK);
    }

    // Delete home policy
    @DeleteMapping(RESTNouns.ID)
    public ResponseEntity<Void> deleteHomePolicy(@PathVariable int id) {
        if (!homePolicyRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        homePolicyRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get home policies by customer ID
    @GetMapping(RESTNouns.CUSTOMER + "/{customerId}")
    public ResponseEntity<List<HomePolicy>> getHomePoliciesByCustomerId(@PathVariable int customerId) {
        List<HomePolicy> policies = homePolicyRepository.findByCustomerId(customerId);
        if (policies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(policies, HttpStatus.OK);
    }

    // Calculate premium for a home policy
    @PostMapping("/calculate")
    public ResponseEntity<HomePolicy> calculateHomePolicy(
            @RequestBody HomePolicyCalculationRequest request) {

        Optional<HomePolicy> policyOpt = homePolicyRepository.findById(request.getPolicyId());
        Optional<Customer> customerOpt = customerRepository.findById(request.getCustomerId());

        if (policyOpt.isEmpty() || customerOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HomePolicy policy = policyOpt.get();
        Customer customer = customerOpt.get();

        // Use the factory to calculate premium
        homePolicyFactory.calculateHomePolicy(policy, customer, request.getDwelling());

        HomePolicy savedPolicy = homePolicyRepository.save(policy);
        return new ResponseEntity<>(savedPolicy, HttpStatus.OK);
    }

    // Inner class for calculation request
    public static class HomePolicyCalculationRequest {
        private int policyId;
        private int customerId;
        private Dwelling dwelling;

        public int getPolicyId() {
            return policyId;
        }

        public void setPolicyId(int policyId) {
            this.policyId = policyId;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public Dwelling getDwelling() {
            return dwelling;
        }

        public void setDwelling(Dwelling dwelling) {
            this.dwelling = dwelling;
        }
    }
}
