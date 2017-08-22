package com.mvplogin.api;

import android.util.Base64;
import android.util.Log;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * 功能
 */

public class ApiClient {

    private static final String TAG = "ApiClient";

    private Retrofit retrofit;
    private LoginApi loginApi;

    private ApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();
        loginApi = retrofit.create(LoginApi.class);
    }

    public static ApiClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ApiClient INSTANCE = new ApiClient();
    }

    public void login(String username, String password) {
        String auth = new String(Base64.encode((username + ":" + password).getBytes(), Base64.NO_WRAP));
        Call<ResponseBody> responseCall = loginApi.login("Basic "+ auth);
        responseCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.i(TAG, "onResponse: " + response.code());
                Log.i(TAG, "onResponse: " + response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());
            }
        });
    }


}
