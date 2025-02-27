package QuotingApplication;

/**
 * The HomePolicy class represents an insurance policy for a home.
 * It stores details about the home such as value, age, heating type, and location,
 * as well as factors that influence the insurance premium calculation.
 */
public class HomePolicy {

    /**
     * The base premium amount for all home policies.
     */
    private final double basePremium = 500;

    /**
     * The value of the home in dollars.
     */
    private double homeValue;

    /**
     * The age of the home in years.
     */
    private int homeAge;

    /**
     * The type of heating system used in the home.
     */
    private String homeHeating;

    /**
     * The location of the home.
     */
    private String location;

    /**
     * The calculated insurance premium for the home.
     */
    private double premium;

    /**
     * Any additional premium amount added due to risk factors.
     */
    private double additional = 0;

    /**
     * Factor applied based on the age of the home.
     */
    private double ageFactor = 0;

    /**
     * Factor applied based on the location of the home.
     */
    private double locationFactor = 0;

    /**
     * Factor applied based on the type of home heating.
     */
    private double heatingFactor = 0;

    /**
     * Constructs a new HomePolicy object with the specified home details.
     *
     * @param homeValue   The value of the home in dollars.
     * @param homeAge     The age of the home in years.
     * @param homeHeating The type of heating system in the home.
     * @param location    The location of the home.
     */
    public HomePolicy(double homeValue, int homeAge, String homeHeating, String location) {
        this.homeValue = homeValue;
        this.homeAge = homeAge;
        this.homeHeating = homeHeating;
        this.location = location;
    }

    /**
     * Gets the base premium for home policies.
     *
     * @return The base premium amount.
     */
    public double getBasePremium() {
        return basePremium;
    }

    /**
     * Gets the value of the home.
     *
     * @return The home value in dollars.
     */
    public double getHomeValue() {
        return homeValue;
    }

    /**
     * Sets the value of the home.
     *
     * @param homeValue The home value in dollars.
     */
    public void setHomeValue(double homeValue) {
        this.homeValue = homeValue;
    }

    /**
     * Gets the age of the home.
     *
     * @return The home age in years.
     */
    public int getHomeAge() {
        return homeAge;
    }

    /**
     * Sets the age of the home.
     *
     * @param homeAge The home age in years.
     */
    public void setHomeAge(int homeAge) {
        this.homeAge = homeAge;
    }

    /**
     * Gets the type of heating system in the home.
     *
     * @return The home heating type.
     */
    public String getHomeHeating() {
        return homeHeating;
    }

    /**
     * Sets the type of heating system in the home.
     *
     * @param homeHeating The home heating type.
     */
    public void setHomeHeating(String homeHeating) {
        this.homeHeating = homeHeating;
    }

    /**
     * Gets the location of the home.
     *
     * @return The home location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the home.
     *
     * @param location The home location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the calculated insurance premium for the home.
     *
     * @return The insurance premium.
     */
    public double getPremium() {
        return premium;
    }

    /**
     * Sets the calculated insurance premium for the home.
     *
     * @param premium The insurance premium.
     */
    public void setPremium(double premium) {
        this.premium = premium;
    }

    /**
     * Gets the additional premium added due to risk factors.
     *
     * @return The additional premium amount.
     */
    public double getAdditional() {
        return additional;
    }

    /**
     * Sets the additional premium amount due to risk factors.
     *
     * @param additional The additional premium amount.
     */
    public void setAdditional(double additional) {
        this.additional = additional;
    }

    /**
     * Gets the age-based factor affecting the premium.
     *
     * @return The age factor.
     */
    public double getAgeFactor() {
        return ageFactor;
    }

    /**
     * Sets the age-based factor affecting the premium.
     *
     * @param ageFactor The age factor.
     */
    public void setAgeFactor(double ageFactor) {
        this.ageFactor = ageFactor;
    }

    /**
     * Gets the location-based factor affecting the premium.
     *
     * @return The location factor.
     */
    public double getLocationFactor() {
        return locationFactor;
    }

    /**
     * Sets the location-based factor affecting the premium.
     *
     * @param locationFactor The location factor.
     */
    public void setLocationFactor(double locationFactor) {
        this.locationFactor = locationFactor;
    }

    /**
     * Gets the heating-based factor affecting the premium.
     *
     * @return The heating factor.
     */
    public double getHeatingFactor() {
        return heatingFactor;
    }

    /**
     * Sets the heating-based factor affecting the premium.
     *
     * @param heatingFactor The heating factor.
     */
    public void setHeatingFactor(double heatingFactor) {
        this.heatingFactor = heatingFactor;
    }
}
