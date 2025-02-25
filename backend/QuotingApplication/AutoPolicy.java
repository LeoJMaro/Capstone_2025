package QuotingApplication;

public class AutoPolicy {
    private final double basePremium = 750;
    private int driverAge;
    private int accidentCount;
    private int vehicleAge;
    private double premium;
    private double ageFactor = 1.0;
    private double accidentFactor = 1.0;
    private double vehicleFactor = 1.0;

    public AutoPolicy(int driverAge, int accidentCount, int vehicleAge) {
        this.driverAge = driverAge;
        this.accidentCount = accidentCount;
        this.vehicleAge = vehicleAge;
    }

    public double getBasePremium() {
        return basePremium;
    }

    public int getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    public int getAccidentCount() {
        return accidentCount;
    }

    public void setAccidentCount(int accidentCount) {
        this.accidentCount = accidentCount;
    }

    public int getVehicleAge() {
        return vehicleAge;
    }

    public void setVehicleAge(int vehicleAge) {
        this.vehicleAge = vehicleAge;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public double getAgeFactor() {
        return ageFactor;
    }

    public void setAgeFactor(double ageFactor) {
        this.ageFactor = ageFactor;
    }

    public double getAccidentFactor() {
        return accidentFactor;
    }

    public void setAccidentFactor(double accidentFactor) {
        this.accidentFactor = accidentFactor;
    }

    public double getVehicleFactor() {
        return vehicleFactor;
    }

    public void setVehicleFactor(double vehicleFactor) {
        this.vehicleFactor = vehicleFactor;
    }
}
