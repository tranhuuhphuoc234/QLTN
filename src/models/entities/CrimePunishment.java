package models.entities;

public class CrimePunishment {
    public String Crime, Punishment;
    public int id;

    public String getCrime() {
        return Crime;
    }

    public void setCrime(String crime) {
        Crime = crime;
    }

    public String getPunishment() {
        return Punishment;
    }

    public void setPunishment(String punishment) {
        Punishment = punishment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
