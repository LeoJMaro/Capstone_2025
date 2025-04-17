package QuotingApplication.controller;

import QuotingApplication.pojos.Policy;
import QuotingApplication.dataaccess.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(RESTNouns.VERSION_1 + RESTNouns.POLICY)
public class PolicyController {

    @Autowired
    private PolicyRepository policyRepository;

    // Get all policies
    @GetMapping
    public Iterable<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    // Get policy by ID
    @GetMapping(RESTNouns.ID)
    public ResponseEntity<Policy> getPolicyById(@PathVariable(name = "id") int id) {
        Optional<Policy> policy = policyRepository.findById(id);
        return policy.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create new policy
    @PostMapping
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
        Policy savedPolicy = policyRepository.save(policy);
        return new ResponseEntity<>(savedPolicy, HttpStatus.CREATED);
    }

    // Update policy
    @PutMapping(RESTNouns.ID)
    public ResponseEntity<Policy> updatePolicy(@PathVariable(name = "id") int id, @RequestBody Policy policy) {
        if (!policyRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        policy.setPolicyId(id);
        Policy updatedPolicy = policyRepository.save(policy);
        return new ResponseEntity<>(updatedPolicy, HttpStatus.OK);
    }

    // Delete policy
    @DeleteMapping(RESTNouns.ID)
    public ResponseEntity<Void> deletePolicy(@PathVariable(name = "id") int id) {
        if (!policyRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        policyRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get policies by customer ID
    @GetMapping(RESTNouns.CUSTOMER + "/{customerId}")
    public ResponseEntity<List<Policy>> getPoliciesByCustomerId(@PathVariable(name = "customerId") int customerId) {
        List<Policy> policies = policyRepository.findByCustomerId(customerId);
        if (policies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(policies, HttpStatus.OK);
    }

    // Get policies by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Policy>> getPoliciesByStatus(@PathVariable(name = "status") String status) {
        List<Policy> policies = policyRepository.findByStatus(status);
        if (policies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(policies, HttpStatus.OK);
    }
}
