package com.mvplogin.api;

import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.mvplogin.OnLoginListener;
import com.mvplogin.module.LoginUser;

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

    //----------------------------------Listener-----------------------------------------//
    private OnLoginListener onLoginListener;

    public void setOnLoginListener(OnLoginListener onLoginListener) {
        this.onLoginListener = onLoginListener;
    }

    //----------------------------------Listener-----------------------------------------//

    public void login(String username, String password) {
        String auth = new String(Base64.encode((username + ":" + password).getBytes(), Base64.NO_WRAP));
        Call<ResponseBody> responseCall = loginApi.login("Basic "+ auth);
        responseCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
               ResponsePrint.print(call, response);
               if (onLoginListener != null) {
                   if (response.code() == HttpCode.SUCCESS) {
                       onLoginListener.onSuccess(ParserResponse.formatGson(response, LoginUser.class));
                   } else {
                       onLoginListener.onError(response.message());
                   }
               }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (onLoginListener != null) {
                    onLoginListener.onError(t.getMessage());
                }
            }
        });
    }
}
