package models.entities;

public class country {
    public String countryname;

    public country(String countryname) {
        this.countryname = countryname;
    }
    public country(){
     countryname = "";
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }
}
