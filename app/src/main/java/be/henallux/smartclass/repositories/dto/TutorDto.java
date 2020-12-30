package be.henallux.smartclass.repositories.dto;



/**
 * Data Transfer Object. This kind of object is used to represent data transferred by a web service.
 * In an application, it is recommended to distinct your DTO from your Model objects, so that you
 * don't expose your database model to the outside
 */
public class TutorDto {

    private String token;

    public TutorDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
