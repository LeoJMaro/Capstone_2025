package QuotingApplication.controller;

import QuotingApplication.pojos.AutoPolicy;
import QuotingApplication.pojos.Vehicle;
import QuotingApplication.dataaccess.AutoPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for Vehicle objects. Since Vehicle is an @Embeddable class
 * used within AutoPolicy entities, this controller provides endpoints to
 * access and update Vehicle information within AutoPolicy entities.
 */
@RestController
@RequestMapping(RESTNouns.VERSION_1 + RESTNouns.VEHICLE)
public class VehicleController {

    @Autowired
    private AutoPolicyRepository autoPolicyRepository;

    // Get vehicle info for a specific auto policy
    @GetMapping("/policy/{policyId}")
    public ResponseEntity<Vehicle> getVehicleByPolicyId(@PathVariable int policyId) {
        Optional<AutoPolicy> policyOpt = autoPolicyRepository.findById(policyId);

        if (policyOpt.isPresent()) {
            AutoPolicy policy = policyOpt.get();
            // Note: In the current POJO model, there is no direct Vehicle field in AutoPolicy
            // This would need to be added to the AutoPolicy class to work properly
            // For now, this returns a placeholder response

            // Placeholder - replace with actual implementation when Vehicle is added to AutoPolicy
            return new ResponseEntity<>(new Vehicle(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update vehicle info for a specific auto policy
    @PutMapping("/policy/{policyId}")
    public ResponseEntity<Vehicle> updateVehicleForPolicy(
            @PathVariable int policyId,
            @RequestBody Vehicle vehicle) {
        System.out.println("Updating vehicle for policy ID: " + policyId);
        System.out.println("Vehicle data: " + vehicle);

        Optional<AutoPolicy> policyOpt = autoPolicyRepository.findById(policyId);

        if (policyOpt.isPresent()) {
            AutoPolicy policy = policyOpt.get();
            // In the current POJO model, there is no direct Vehicle field in AutoPolicy
            // This would need to be added to the AutoPolicy class to work properly
            // For now, this returns a placeholder response

            //TODO
            // replace with actual implementation when Vehicle is added to AutoPolicy
            // policy.setVehicle(vehicle);
            // autoPolicyRepository.save(policy);

            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Create a new vehicle validation endpoint
    @PostMapping("/validate")
    public ResponseEntity<VehicleValidationResponse> validateVehicle(@RequestBody Vehicle vehicle) {
        VehicleValidationResponse response = new VehicleValidationResponse();

        // Validate vehicle make
        if (vehicle.getVehicleMake() == null || vehicle.getVehicleMake().isEmpty()) {
            response.setValid(false);
            response.addError("vehicleMake", "Vehicle make is required");
        }

        // Validate vehicle model
        if (vehicle.getVehicleModel() == null || vehicle.getVehicleModel().isEmpty()) {
            response.setValid(false);
            response.addError("vehicleModel", "Vehicle model is required");
        }

        // Validate vehicle year
        int currentYear = java.time.LocalDate.now().getYear();
        if (vehicle.getVehicleYear() < 1900 || vehicle.getVehicleYear() > currentYear + 1) {
            response.setValid(false);
            response.addError("vehicleYear", "Vehicle year must be between 1900 and " + (currentYear + 1));
        }

        // Validate vehicle accidents
        if (vehicle.getVehicleAccidents() < 0) {
            response.setValid(false);
            response.addError("vehicleAccidents", "Number of accidents cannot be negative");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Inner class for validation response
    public static class VehicleValidationResponse {
        private boolean valid = true;
        private java.util.Map<String, String> errors = new java.util.HashMap<>();

        public boolean isValid() {return valid;}

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public java.util.Map<String, String> getErrors() {return errors;}

        public void setErrors(java.util.Map<String, String> errors) {
            this.errors = errors;
        }

        public void addError(String field, String message) {
            this.errors.put(field, message);
        }
    }
}

