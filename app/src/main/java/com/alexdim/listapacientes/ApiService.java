package com.alexdim.listapacientes;

import java.util.List;

import okhttp3.Callback;
import okhttp3.MediaType;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/api/Persona")
    Call<List<Post>> getPost();

    @GET("/api/Persona/{id}")
    Call<Post> getPostById(@Path("id") int postId);

    @POST("/api/Usuario")
    Call<User> post(@Body User user);

    /*@FormUrlEncoded
    //@Headers("Content-Type: application/json")
    @POST("/api/Usuario")
    //Call<User> post(@Body String user);
    Call<User> post(
            @Field("nombre") String nombre,
            @Field("contra") String contra
            /*@Field("nombre") String nombre,
            @Field("contra") String contra
    )*/
    //Call<User> post(@Body User user);

    /*@FormUrlEncoded
    @POST("/api/Usuario")
    Call<User> post(
            @Field("nombre") String nombre,
            @Field("contra") String contra
    );*/

}