package com.example.mycreateleague.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Post {

    public static String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<DataModel>> getJSON();

    @POST("android/jsonandroid")
    @FormUrlEncoded
    Call<DataModel> getJSON1(@Field("CostomerID") String CostomerID);

}
