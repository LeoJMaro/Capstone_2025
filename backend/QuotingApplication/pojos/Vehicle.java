package pojos;

import jakarta.persistence.*;

@Entity
//@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleId;

    private String vehicleMake;
    private String vehicleModel;
    private int vehicleYear;
    private int vehicleAccidents;

    public Vehicle() {
    }

    public Vehicle(String vehicleMake, String vehicleModel, int vehicleYear, int vehicleAccidents) {
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.vehicleAccidents = vehicleAccidents;
    }


    public int getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
    public String getVehicleMake() {
        return vehicleMake;
    }
    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }
    public String getVehicleModel() {
        return vehicleModel;
    }
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
    public int getVehicleYear() {
        return vehicleYear;
    }
    public void setVehicleYear(int vehicleYear) {
        this.vehicleYear = vehicleYear;
    }
    public int getVehicleAccidents() {
        return vehicleAccidents;
    }
    public void setVehicleAccidents(int vehicleAccidents) {
        this.vehicleAccidents = vehicleAccidents;
    }
}
