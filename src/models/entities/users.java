package models.entities;

public class users {
    public String username,password;
    boolean check;

    public users(String username, String password, boolean check) {
        this.username = username;
        this.password = password;
        this.check = false;
    }
    public users(){
        username = "";
        password = "";
        check = false;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
