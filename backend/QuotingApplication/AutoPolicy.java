package QuotingApplication;

/**
 * The AutoPolicy class represents an auto insurance policy,
 * including factors like driver age, accident count, and vehicle age
 * that contribute to the policy's premium calculation.
 * <p>
 * The base premium is a fixed starting value to which various factors are applied.
 */
public class AutoPolicy {

    /**
     * The base premium amount for all policies.
     */
    private final double basePremium = 750;

    /**
     * The age of the driver in years.
     */
    private int driverAge;

    /**
     * The number of accidents the driver has been involved in.
     */
    private int accidentCount;

    /**
     * The age of the vehicle in years.
     */
    private int vehicleAge;

    /**
     * The calculated premium for the policy.
     */
    private double premium;

    /**
     * Factor applied to the premium based on the driver's age.
     */
    private double ageFactor = 0;

    /**
     * Factor applied to the premium based on the driver's accident history.
     */
    private double accidentFactor = 0;

    /**
     * Factor applied to the premium based on the vehicle's age.
     */
    private double vehicleFactor = 0;

    /**
     * Constructs a new AutoPolicy with the specified driver age, accident count, and vehicle age.
     *
     * @param driverAge     the age of the driver
     * @param accidentCount the number of accidents the driver has been involved in
     * @param vehicleAge    the age of the vehicle
     */
    public AutoPolicy(int driverAge, int accidentCount, int vehicleAge) {
        this.driverAge = driverAge;
        this.accidentCount = accidentCount;
        this.vehicleAge = vehicleAge;
    }

    /**
     * Returns the base premium amount.
     *
     * @return the base premium
     */
    public double getBasePremium() {
        return basePremium;
    }

    /**
     * Returns the driver's age.
     *
     * @return the driver's age
     */
    public int getDriverAge() {
        return driverAge;
    }

    /**
     * Sets the driver's age.
     *
     * @param driverAge the new driver age
     */
    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    /**
     * Returns the number of accidents.
     *
     * @return the number of accidents
     */
    public int getAccidentCount() {
        return accidentCount;
    }

    /**
     * Sets the number of accidents.
     *
     * @param accidentCount the new accident count
     */
    public void setAccidentCount(int accidentCount) {
        this.accidentCount = accidentCount;
    }

    /**
     * Returns the vehicle age.
     *
     * @return the vehicle age
     */
    public int getVehicleAge() {
        return vehicleAge;
    }

    /**
     * Sets the vehicle age.
     *
     * @param vehicleAge the new vehicle age
     */
    public void setVehicleAge(int vehicleAge) {
        this.vehicleAge = vehicleAge;
    }

    /**
     * Returns the calculated premium.
     *
     * @return the premium
     */
    public double getPremium() {
        return premium;
    }

    /**
     * Sets the calculated premium.
     *
     * @param premium the new premium value
     */
    public void setPremium(double premium) {
        this.premium = premium;
    }

    /**
     * Returns the age factor applied to the premium.
     *
     * @return the age factor
     */
    public double getAgeFactor() {
        return ageFactor;
    }

    /**
     * Sets the age factor applied to the premium.
     *
     * @param ageFactor the new age factor
     */
    public void setAgeFactor(double ageFactor) {
        this.ageFactor = ageFactor;
    }

    /**
     * Returns the accident factor applied to the premium.
     *
     * @return the accident factor
     */
    public double getAccidentFactor() {
        return accidentFactor;
    }

    /**
     * Sets the accident factor applied to the premium.
     *
     * @param accidentFactor the new accident factor
     */
    public void setAccidentFactor(double accidentFactor) {
        this.accidentFactor = accidentFactor;
    }

    /**
     * Returns the vehicle factor applied to the premium.
     *
     * @return the vehicle factor
     */
    public double getVehicleFactor() {
        return vehicleFactor;
    }

    /**
     * Sets the vehicle factor applied to the premium.
     *
     * @param vehicleFactor the new vehicle factor
     */
    public void setVehicleFactor(double vehicleFactor) {
        this.vehicleFactor = vehicleFactor;
    }
}
