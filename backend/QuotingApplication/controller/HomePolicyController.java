package controller;

import factories.HomePolicyFactory;
import pojos.Customer;
import pojos.Dwelling;
import pojos.HomePolicy;
import dataaccess.CustomerRepository;
import dataaccess.HomePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        HomePolicy savedPolicy = homePolicyRepository.save(policy);
        return new ResponseEntity<>(savedPolicy, HttpStatus.CREATED);
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

