package com.mvplogin.api;

import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * 功能
 */

public interface LoginApi {


    @POST("user")
    Call<ResponseBody> login(
           @Header("Authorization") String authorization
    );

}
