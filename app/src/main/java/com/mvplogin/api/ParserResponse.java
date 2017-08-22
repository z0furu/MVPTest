package com.mvplogin.api;


import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * 功能
 */

public class ParserResponse {

    public static String response(Response<ResponseBody> response) {
        try {
            return response.body().string();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return " response body =  Null ";
        }
    }

    public static <T> T formatGson(Response<ResponseBody> responseBodyResponse, Class<T> classOfT) {
        return new Gson().fromJson(response(responseBodyResponse), classOfT);
    }
}
