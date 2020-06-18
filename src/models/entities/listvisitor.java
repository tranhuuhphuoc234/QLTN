package models.entities;

public class listvisitor {
    public String visitorid,visitorname,visitorphone,visitoraddress, relationship;
    public int city;
    public int country;

    public String getVisitorid() {
        return visitorid;
    }

    public void setVisitorid(String visitorid) {
        this.visitorid = visitorid;
    }

    public String getVisitorname() {
        return visitorname;
    }

    public void setVisitorname(String visitorname) {
        this.visitorname = visitorname;
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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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
}
