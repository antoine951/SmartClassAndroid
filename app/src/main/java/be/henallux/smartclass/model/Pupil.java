package be.henallux.smartclass.model;

public class Pupil {

    private String login;
    private String password;
    private String token;
    private String firstname;
    private String lastname;

    public Pupil() { }

    public Pupil(String token, String firstname, String lastname) {
        this.token = token;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Pupil(String login, String password, String firstname, String lastname) {
        setLogin(login);
        setPassword(password);
        setFirstname(firstname);
        setLastname(lastname);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
