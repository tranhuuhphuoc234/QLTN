package models.entities;

import java.sql.Timestamp;

public class prisoner {
    public String prisoneridcard,prisonername,gender,address;
    public int prisonerage,crime,dangerlevel,punishment,cellroom,city,relative,country;
    public Timestamp dateofbirth, dateofarrest,dateofrelease;

    public Timestamp getDateofrelease() {
        return dateofrelease;
    }

    public void setDateofrelease(Timestamp dateofrelease) {
        this.dateofrelease = dateofrelease;
    }

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

    public int getRelative() {
        return relative;
    }

    public void setRelative(int relative) {
        this.relative = relative;
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
