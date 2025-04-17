package QuotingApplication.pojos;

import jakarta.persistence.*;

@Embeddable
public class Dwelling {

    private String dwellingType;
    private String heatingType;
    private String location;
    private int age;
    private double homeValue;

    public Dwelling() {
    }

    public Dwelling(String dwellingType, String heatingType, String location, int age, double homeValue) {
        this.dwellingType = dwellingType;
        this.heatingType = heatingType;
        this.location = location;
        this.age = age;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double getHomeValue() {
        return homeValue;
    }
    public void setHomeValue(double homeValue) {
        this.homeValue = homeValue;
    }
}
