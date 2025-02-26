package QuotingApplication;

public class HomePolicy {
    private final double basePremium = 500;
    private double homeValue;
    private int homeAge;
    private String homeHeating;
    private String location;
    private double premium;
    private double additional = 0;

    // Factors
    private double ageFactor = 0;
    private double locationFactor = 0;
    private double heatingFactor = 0;

    public HomePolicy(double homeValue, int homeAge, String homeHeating, String location) {
        this.homeValue = homeValue;
        this.homeAge = homeAge;
        this.homeHeating = homeHeating;
        this.location = location;
    }

    public double getBasePremium() {
        return basePremium;
    }

    public double getHomeValue() {
        return homeValue;
    }

    public void setHomeValue(double homeValue) {
        this.homeValue = homeValue;
    }

    public int getHomeAge() {
        return homeAge;
    }

    public void setHomeAge(int homeAge) {
        this.homeAge = homeAge;
    }

    public String getHomeHeating() {
        return homeHeating;
    }

    public void setHomeHeating(String homeHeating) {
        this.homeHeating = homeHeating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public double getAdditional() {
        return additional;
    }

    public void setAdditional(double additional) {
        this.additional = additional;
    }

    public double getAgeFactor() {
        return ageFactor;
    }

    public void setAgeFactor(double ageFactor) {
        this.ageFactor = ageFactor;
    }

    public double getLocationFactor() {
        return locationFactor;
    }

    public void setLocationFactor(double locationFactor) {
        this.locationFactor = locationFactor;
    }

    public double getHeatingFactor() {
        return heatingFactor;
    }

    public void setHeatingFactor(double heatingFactor) {
        this.heatingFactor = heatingFactor;
    }
}
