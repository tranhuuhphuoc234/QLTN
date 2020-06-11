package models.entities;

import java.sql.Timestamp;

public class prisonerlist {
    public String prisoneridcard,prisonername,gender,address;
    public int prisonerid,prisonerage,crime,dangerlevel,punishment,cellroom,city,country;
    public String dateofbirth, dateofarrest,dateofrelease;

    public String getPrisoneridcard() {
        return prisoneridcard;
    }

    public void setPrisoneridcard(String prisoneridcard) {
        this.prisoneridcard = prisoneridcard;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrisonerid() {
        return prisonerid;
    }

    public void setPrisonerid(int prisonerid) {
        this.prisonerid = prisonerid;
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

    public int getDangerlevel() {
        return dangerlevel;
    }

    public void setDangerlevel(int dangerlevel) {
        this.dangerlevel = dangerlevel;
    }

    public int getPunishment() {
        return punishment;
    }

    public void setPunishment(int punishment) {
        this.punishment = punishment;
    }

    public int getCellroom() {
        return cellroom;
    }

    public void setCellroom(int cellroom) {
        this.cellroom = cellroom;
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

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getDateofarrest() {
        return dateofarrest;
    }

    public void setDateofarrest(String dateofarrest) {
        this.dateofarrest = dateofarrest;
    }

    public String getDateofrelease() {
        return dateofrelease;
    }

    public void setDateofrelease(String dateofrelease) {
        this.dateofrelease = dateofrelease;
    }
}
