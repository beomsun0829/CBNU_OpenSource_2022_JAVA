package com.example.practice;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Retrofit_interface {

    @GET("api/locationsearch")
        //api/location
    Call<List<data_model>> test_api_get(@Query("name") String faclNm);
    //@Query("name") String faclNm

//    @POST("api/location?name=")
//    Call<data_model> postOverlapCheck(@Body data_model data__model);
}
