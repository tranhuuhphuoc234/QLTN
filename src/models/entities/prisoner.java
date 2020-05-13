package models.entities;

import java.sql.Timestamp;

public class prisoner {
    public String prisonername,gender,phone,address;
    public int prisonerage,crime,punishment,city,country;
    public Timestamp dateofbirth,dateofarrest;

    public String getPrisonername() {
        return prisonername;
    }

    public void setPrisonername(String prisonername) {
        this.prisonername = prisonername;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrisonerage() {
        return prisonerage;
    }

    public void setPrisonerage(int prisonerage) {
        this.prisonerage = prisonerage;
    }

    public int getCrime() {
        return crime;
    }

    public void setCrime(int crime) {
        this.crime = crime;
    }

    public int getPunishment() {
        return punishment;
    }

    public void setPunishment(int punishment) {
        this.punishment = punishment;
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

    public Timestamp getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Timestamp dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public Timestamp getDateofarrest() {
        return dateofarrest;
    }

    public void setDateofarrest(Timestamp dateofarrest) {
        this.dateofarrest = dateofarrest;
    }
}
