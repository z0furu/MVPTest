package com.mvplogin.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * 功能
 */

public class ResponsePrint {

    private static final String TAG = "API Response";

    public static synchronized void print(Call<ResponseBody> call, Response<ResponseBody> response) {
        Log.i(TAG, " method = " + call.request().method());
        Log.i(TAG, " url = " + call.request().url());
        Log.i(TAG, " code = " + response.code());
        Log.i(TAG, " message = " + response.message());
    }
}
