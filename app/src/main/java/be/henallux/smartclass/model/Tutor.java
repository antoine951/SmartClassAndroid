package be.henallux.smartclass.model;

public class Tutor {

    private String username;
    private String password;

    public Tutor() { }

    public Tutor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
