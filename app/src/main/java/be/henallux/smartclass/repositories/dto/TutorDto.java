package be.henallux.smartclass.repositories.dto;

import com.squareup.moshi.Json;

/**
 * Data Transfer Object. This kind of object is used to represent data transferred by a web service.
 * In an application, it is recommended to distinct your DTO from your Model objects, so that you
 * don't expose your database model to the outside
 */
public class TutorDto {


    private String username;
    private String password;

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
