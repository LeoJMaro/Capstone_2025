package QuotingApplication.controller;

import QuotingApplication.factories.AutoPolicyFactory;
import QuotingApplication.pojos.AutoPolicy;
import QuotingApplication.pojos.Customer;
import QuotingApplication.pojos.Vehicle;
import QuotingApplication.dataaccess.AutoPolicyRepository;
import QuotingApplication.dataaccess.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(RESTNouns.VERSION_1 + RESTNouns.AUTO_POLICY)
public class AutoPolicyController {

    @Autowired
    private AutoPolicyRepository autoPolicyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AutoPolicyFactory autoPolicyFactory;

    // Get all auto policies
    @GetMapping
    public Iterable<AutoPolicy> getAllAutoPolicies() {
        return autoPolicyRepository.findAll();
    }

    // Get auto policy by ID
    @GetMapping(RESTNouns.ID)
    public ResponseEntity<AutoPolicy> getAutoPolicyById(@PathVariable int id) {
        Optional<AutoPolicy> policy = autoPolicyRepository.findById(id);
        return policy.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create new auto policy
    @PostMapping
    public ResponseEntity<AutoPolicy> createAutoPolicy(@RequestBody AutoPolicy policy) {
        Optional<Customer> customerOpt = customerRepository.findById(policy.getCustomerId());
        if (customerOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Calculate and set premium
        autoPolicyFactory.calculateAutoPolicy(policy, policy.getCustomerId(), policy.getVehicle(), List.of(customerOpt.get()));

        AutoPolicy savedPolicy = autoPolicyRepository.save(policy);
        return new ResponseEntity<>(savedPolicy, HttpStatus.CREATED);
    }

    // Update auto policy
    @PutMapping(RESTNouns.ID)
    public ResponseEntity<AutoPolicy> updateAutoPolicy(@PathVariable int id, @RequestBody AutoPolicy policy) {
        if (!autoPolicyRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        policy.setPolicyId(id);
        AutoPolicy updatedPolicy = autoPolicyRepository.save(policy);
        return new ResponseEntity<>(updatedPolicy, HttpStatus.OK);
    }

    // Delete auto policy
    @DeleteMapping(RESTNouns.ID)
    public ResponseEntity<Void> deleteAutoPolicy(@PathVariable int id) {
        if (!autoPolicyRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        autoPolicyRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get auto policies by customer ID
    @GetMapping(RESTNouns.CUSTOMER + "/{customerId}")
    public ResponseEntity<List<AutoPolicy>> getAutoPoliciesByCustomerId(@PathVariable int customerId) {
        List<AutoPolicy> policies = autoPolicyRepository.findByCustomerId(customerId);
        if (policies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(policies, HttpStatus.OK);
    }

    // Calculate premium for an auto policy
    @PostMapping("/calculate")
    public ResponseEntity<AutoPolicy> calculateAutoPolicy(@RequestBody AutoPolicyCalculationRequest request) {
        Optional<AutoPolicy> policyOpt = autoPolicyRepository.findById(request.getPolicyId());
        Optional<Customer> customerOpt = customerRepository.findById(request.getCustomerId());

        if (policyOpt.isEmpty() || customerOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AutoPolicy policy = policyOpt.get();
        List<Customer> customers = new ArrayList<>();
        customers.add(customerOpt.get());

        // Use factory method to calculate premium
        autoPolicyFactory.calculateAutoPolicy(policy, request.getCustomerId(), request.getVehicle(), customers);

        AutoPolicy savedPolicy = autoPolicyRepository.save(policy);
        return new ResponseEntity<>(savedPolicy, HttpStatus.OK);
    }

    // Inner class for calculation request
    public static class AutoPolicyCalculationRequest {
        private int policyId;
        private int customerId;
        private Vehicle vehicle;

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

        public Vehicle getVehicle() {
            return vehicle;
        }

        public void setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
        }
    }
}
