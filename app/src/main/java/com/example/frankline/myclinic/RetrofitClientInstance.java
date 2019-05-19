package com.example.frankline.myclinic;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private  static Retrofit retrofit;
    private  static final String BASE_URL = "http://192.168.43.190:8000/";

    public static Retrofit getRetrofitInstance(){
        if (retrofit==null){
            retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        }
        return retrofit;
    }
}