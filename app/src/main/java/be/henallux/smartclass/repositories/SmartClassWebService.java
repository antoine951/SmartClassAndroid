package be.henallux.smartclass.repositories;

import be.henallux.smartclass.repositories.dto.TutorDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Java Interface, exposing your different endpoints. </br>
 * If you would like to mock your backend server to test your application, you can use one of those
 * different tools : </br>
 * - Mockoon; </br>
 * - Beeceptor; </br>
 * - ...; </br>
 *
 * If you would like to visualize the content of your web API, you can also use a
 * REST API Client Interface. It can be really useful for you to check the structure of JSON
 * sent / needed by your API. You can use one of those : </br>
 * - Postman; </br>
 * - Insomnia; </br>
 * - ...;
 */
public interface SmartClassWebService {

    @POST("/login")
    Call<TutorDto> getLogin();
}
