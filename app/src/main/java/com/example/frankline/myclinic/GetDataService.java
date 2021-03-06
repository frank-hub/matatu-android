package com.example.frankline.myclinic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetDataService {
    @GET("/api/test")
    Call<List<Retro>> getAllTests();

    @POST("api/book")
    Call<Retro> saveBooking(@Body Retro retro);
//    @FormUrlEncoded
//    Call<Retro> saveBooking(@Field("name")String name,
//                            @Field("email")String email,
//                            @Field("phone")String phone,
//                            @Field("start")String start,
//                            @Field("illness")String illness);

    @DELETE("api/destroy/{id}")
    Call<Retro> deleteBooking(@Path("id") int id);

    @FormUrlEncoded
    @POST("api/login")
    Call<UserToken> getLoginToken(@Field("email") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/register")
    Call<UserToken> registerUser(@Field("email") String email, @Field("name") String username, @Field("phone") String phone, @Field("password") String password);

//    @Headers({
//            "Accept:application/json",
//            "AUTH:API"
//    })
//    @GET("rides")
//    Call<List<Ride>> getRides();
}
