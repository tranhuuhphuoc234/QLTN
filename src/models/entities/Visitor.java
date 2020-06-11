package models.entities;
public class Visitor {
            public int visitorid,city,country;
            public String visitoridcard, visitorname,gender,visitorphone,visitoraddress;

    public Visitor(int visitorid, int city, int country, String visitoridcard, String visitorname, String gender, String visitorphone, String visitoraddress) {
        this.visitorid = visitorid;
        this.city = city;
        this.country = country;
        this.visitoridcard = visitoridcard;
        this.visitorname = visitorname;
        this.gender = gender;
        this.visitorphone = visitorphone;
        this.visitoraddress = visitoraddress;
    }
    public Visitor(){
        visitorid = 0;
        visitoridcard = "";
        visitorname = "";
        gender = "";
        visitorphone = "";
        visitoraddress = "";
        city = 0;
        country = 0;
    }

    public int getVisitorid() {
        return visitorid;
    }

    public void setVisitorid(int visitorid) {
        this.visitorid = visitorid;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getVisitoridcard() {
        return visitoridcard;
    }

    public void setVisitoridcard(String visitoridcard) {
        this.visitoridcard = visitoridcard;
    }

    public String getVisitorname() {
        return visitorname;
    }

    public void setVisitorname(String visitorname) {
        this.visitorname = visitorname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVisitorphone() {
        return visitorphone;
    }

    public void setVisitorphone(String visitorphone) {
        this.visitorphone = visitorphone;
    }

    public String getVisitoraddress() {
        return visitoraddress;
    }

    public void setVisitoraddress(String visitoraddress) {
        this.visitoraddress = visitoraddress;
    }
}