package com.mvplogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.mvplogin.api.ApiClient;

/**
 * 功能
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected ApiClient apiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceID());

        bindView();
        viewClickListener();

        apiClient = ApiClient.getInstance();

    }

    abstract int getResourceID();

    abstract void bindView();

    abstract void viewClickListener();

    protected String edtToString(TextInputEditText editText) {
        if (editText == null) return "";
        else return editText.getText().toString();
    }



}
