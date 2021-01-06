package be.henallux.smartclass.repositories;


import java.util.ArrayList;

import be.henallux.smartclass.model.Tutor;
import be.henallux.smartclass.model.requestLogin;
import be.henallux.smartclass.repositories.dto.EventDto;
import be.henallux.smartclass.repositories.dto.PupilDto;
import be.henallux.smartclass.repositories.dto.TaskDto;
import be.henallux.smartclass.repositories.dto.TestDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


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

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("/login")
    Call<String> login(@Body requestLogin requestLogin);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("/signup")
    Call<Void> signUp(@Body Tutor tutor);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("/tutor/add/pupil")
    Call<Void> addchild(@Header("Authorization") String token, @Body requestLogin requestLogin);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/tutor/pupils")
    Call<ArrayList<PupilDto>> getChildren(@Header("Authorization") String token);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/tasks/week")
    Call<ArrayList<TaskDto>> getTasks(@Header("Authorization") String token);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/test")
    Call<ArrayList<TestDto>> getUnsignedTests(@Header("Authorization") String token);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/events")
    Call<ArrayList<EventDto>> getEvents(@Header("Authorization") String token);







}
