package QuotingApplication.pojos;

public class Vehicle {
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleYear;
    private int vehicleAccidents;

    public Vehicle(String vehicleMake, String vehicleModel, int vehicleYear, int vehicleAccidents) {
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.vehicleAccidents = vehicleAccidents;
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
