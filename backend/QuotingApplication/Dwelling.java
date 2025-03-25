package QuotingApplication;

public class Dwelling {
    private String dwellingType;
    private String heatingType;
    private String location;
    private int ageBuilt;
    private double homeValue;

    public Dwelling(String dwellingType, String heatingType, String location, int ageBuilt, double homeValue) {
        this.dwellingType = dwellingType;
        this.heatingType = heatingType;
        this.location = location;
        this.ageBuilt = ageBuilt;
        this.homeValue = homeValue;
    }

    public String getDwellingType() {
        return dwellingType;
    }

    public void setDwellingType(String dwellingType) {
        this.dwellingType = dwellingType;
    }

    public String getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(String heatingType) {
        this.heatingType = heatingType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAgeBuilt() {
        return ageBuilt;
    }

    public void setAgeBuilt(int ageBuilt) {
        this.ageBuilt = ageBuilt;
    }

    public double getHomeValue() {
        return homeValue;
    }

    public void setHomeValue(double homeValue) {
        this.homeValue = homeValue;
    }
}
