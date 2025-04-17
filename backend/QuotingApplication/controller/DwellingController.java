package QuotingApplication.controller;

import QuotingApplication.pojos.Dwelling;
import QuotingApplication.pojos.HomePolicy;
import QuotingApplication.dataaccess.HomePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

/**
 * Controller for Dwelling objects. Since Dwelling is an @Embeddable class
 * used within HomePolicy entities, this controller provides endpoints to
 * access and update Dwelling information within HomePolicy entities.
 */
@RestController
@RequestMapping(RESTNouns.VERSION_1 + RESTNouns.DWELLING)
public class DwellingController {

    @Autowired
    private HomePolicyRepository homePolicyRepository;

    // Get dwelling info for a specific home policy
    @GetMapping("/policy/{policyId}")
    public ResponseEntity<Dwelling> getDwellingByPolicyId(@PathVariable(name = "policyId") int policyId) {
        Optional<HomePolicy> policyOpt = homePolicyRepository.findById(policyId);

        if (policyOpt.isPresent()) {
            HomePolicy policy = policyOpt.get();
            Dwelling dwelling = policy.getDwelling();

            if (dwelling != null) {
                return new ResponseEntity<>(dwelling, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update dwelling info for a specific home policy
    @PutMapping("/policy/{policyId}")
    public ResponseEntity<Dwelling> updateDwellingForPolicy(
            @PathVariable(name = "policyId") int policyId,
            @RequestBody Dwelling dwelling) {

        Optional<HomePolicy> policyOpt = homePolicyRepository.findById(policyId);

        if (policyOpt.isPresent()) {
            HomePolicy policy = policyOpt.get();
            policy.setDwelling(dwelling);
            homePolicyRepository.save(policy);

            return new ResponseEntity<>(dwelling, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Create a new dwelling validation endpoint
    @PostMapping("/validate")
    public ResponseEntity<DwellingValidationResponse> validateDwelling(@RequestBody Dwelling dwelling) {
        DwellingValidationResponse response = new DwellingValidationResponse();

        // Validate dwelling type
        if (dwelling.getDwellingType() == null || dwelling.getDwellingType().isEmpty()) {
            response.setValid(false);
            response.addError("dwellingType", "Dwelling type is required");
        }

        // Validate heating type
        if (dwelling.getHeatingType() == null || dwelling.getHeatingType().isEmpty()) {
            response.setValid(false);
            response.addError("heatingType", "Heating type is required");
        }

        // Validate location
        if (dwelling.getLocation() == null || dwelling.getLocation().isEmpty()) {
            response.setValid(false);
            response.addError("location", "Location is required");
        }

        // Validate age
        if (dwelling.getAge() < 0) {
            response.setValid(false);
            response.addError("age", "Age cannot be negative");
        }

        // Validate home value
        if (dwelling.getHomeValue() <= 0) {
            response.setValid(false);
            response.addError("homeValue", "Home value must be greater than zero");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Inner class for validation response
    public static class DwellingValidationResponse {
        private boolean valid = true;
        private java.util.Map<String, String> errors = new java.util.HashMap<>();

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public java.util.Map<String, String> getErrors() {
            return errors;
        }

        public void setErrors(java.util.Map<String, String> errors) {
            this.errors = errors;
        }

        public void addError(String field, String message) {
            this.errors.put(field, message);
        }
    }
}
