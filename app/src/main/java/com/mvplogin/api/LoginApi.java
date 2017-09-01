package com.mvplogin.api;

import com.mvplogin.model.LoginUser;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * 功能
 */

public interface LoginApi {


    @GET("user")
    Observable<LoginUser> login(
           @Header("Authorization") String authorization
    );

}
