package models.entities;

public class city {
public String cityname;
public int country;

    public city(String cityname, int country) {
        this.cityname = cityname;
        this.country = country;
    }

    public city() {
        cityname = "";
        country = 0;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }
}
