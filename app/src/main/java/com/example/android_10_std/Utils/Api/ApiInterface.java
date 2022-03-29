package com.example.android_10_std.Utils.Api;

import com.example.android_10_std.Screen.Api_Screen.Model.Api_Model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("users")
    Call<Api_Model> postData(@Field("name")String name, @Field("job")String job);

}
