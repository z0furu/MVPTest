package com.mvplogin;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.mvplogin.module.LoginUser;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends BaseActivity implements View.OnClickListener, OnLoginListener{

    private static final String TAG = "LoginActivity";

    private Button btnLogin;
    private TextInputLayout txtInputAccount;
    private TextInputLayout txtInputPassword;
    private TextInputEditText edtAccount;
    private TextInputEditText edtPassword;
    private ProgressBar progressBar;


    @Override
    int getResourceID() {
        return R.layout.activity_login;
    }

    @Override
    void bindView() {
        btnLogin =  findViewById(R.id.btn_login);
        txtInputAccount =  findViewById(R.id.txt_input_account);
        txtInputPassword =  findViewById(R.id.txt_input_password);
        edtAccount =  findViewById(R.id.edt_account);
        edtPassword =  findViewById(R.id.edt_password);
        progressBar = findViewById(R.id.progressBar);

    }

    @Override
    void viewClickListener() {
        btnLogin.setOnClickListener(this);
    }

    private boolean validInput() {
        if (TextUtils.isEmpty(edtToString(edtAccount))) {
            txtInputAccount.setError(getString(R.string.login_account_not_empty));
            return false;
        } else {
            txtInputAccount.setError(null);
        }
        if (TextUtils.isEmpty(edtToString(edtPassword))) {
            txtInputPassword.setError(getString(R.string.login_password_not_empty));
            return false;
        } else {
            txtInputPassword.setError(null);
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (validInput()) {
                    btnLogin.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    apiClient.setOnLoginListener(this);
                    apiClient.login(edtToString(edtAccount), edtToString(edtPassword));
                }
                break;
        }
    }

    @Override
    public void onSuccess(LoginUser loginUser) {
        btnLogin.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        Toasty.normal(this, "Hello " + loginUser.getName()).show();
    }

    @Override
    public void onError(String errorMessage) {
        btnLogin.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        Toasty.error(this, errorMessage).show();
    }
}
