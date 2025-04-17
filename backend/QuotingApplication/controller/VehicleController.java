package QuotingApplication.controller;

import QuotingApplication.pojos.AutoPolicy;
import QuotingApplication.pojos.Vehicle;
import QuotingApplication.dataaccess.AutoPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(RESTNouns.VERSION_1 + RESTNouns.VEHICLE)  // /v1/vehicles
public class VehicleController {

    @Autowired
    private AutoPolicyRepository autoPolicyRepository;

    // Get vehicle info for a specific auto policy
    @GetMapping("/policy/{policyId}")
    public ResponseEntity<Vehicle> getVehicleByPolicyId(@PathVariable(name = "policyId") int policyId) {
        Optional<AutoPolicy> policyOpt = autoPolicyRepository.findById(policyId);

        if (policyOpt.isPresent()) {
            Vehicle vehicle = policyOpt.get().getVehicle();
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update vehicle info for a specific auto policy
    @PutMapping("/policy/{policyId}")
    public ResponseEntity<Vehicle> updateVehicleForPolicy(
            @PathVariable(name = "policyId") int policyId,
            @RequestBody Vehicle vehicle) {

        Optional<AutoPolicy> policyOpt = autoPolicyRepository.findById(policyId);

        if (policyOpt.isPresent()) {
            AutoPolicy policy = policyOpt.get();
            policy.setVehicle(vehicle);
            autoPolicyRepository.save(policy);
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Validate a vehicle object (used on creation/edit screens)
    @PostMapping("/validate")
    public ResponseEntity<VehicleValidationResponse> validateVehicle(@RequestBody Vehicle vehicle) {
        VehicleValidationResponse response = new VehicleValidationResponse();

        if (vehicle.getVehicleMake() == null || vehicle.getVehicleMake().isEmpty()) {
            response.setValid(false);
            response.addError("vehicleMake", "Vehicle make is required");
        }

        if (vehicle.getVehicleModel() == null || vehicle.getVehicleModel().isEmpty()) {
            response.setValid(false);
            response.addError("vehicleModel", "Vehicle model is required");
        }

        int currentYear = java.time.LocalDate.now().getYear();
        if (vehicle.getVehicleYear() < 1900 || vehicle.getVehicleYear() > currentYear + 1) {
            response.setValid(false);
            response.addError("vehicleYear", "Vehicle year must be between 1900 and " + (currentYear + 1));
        }

        if (vehicle.getVehicleAccidents() < 0) {
            response.setValid(false);
            response.addError("vehicleAccidents", "Number of accidents cannot be negative");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static class VehicleValidationResponse {
        private boolean valid = true;
        private java.util.Map<String, String> errors = new java.util.HashMap<>();

        public boolean isValid() { return valid; }

        public void setValid(boolean valid) { this.valid = valid; }

        public java.util.Map<String, String> getErrors() { return errors; }

        public void setErrors(java.util.Map<String, String> errors) { this.errors = errors; }

        public void addError(String field, String message) {
            this.errors.put(field, message);
        }
    }
}
