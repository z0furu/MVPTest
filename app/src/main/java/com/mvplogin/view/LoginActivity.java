package com.mvplogin.view;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


import com.mvplogin.BaseActivity;
import com.mvplogin.R;
import com.mvplogin.listener.OnLoginListener;
import com.mvplogin.module.LoginUser;
import com.mvplogin.presenter.ILoginPresenter;
import com.mvplogin.presenter.LoginPresenter;
import com.mvplogin.utils.InputHelper;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView {

    private static final String TAG = "LoginActivity";

    private Button btnLogin;
    private TextInputLayout txtInputAccount;
    private TextInputLayout txtInputPassword;
    private TextInputEditText edtAccount;
    private TextInputEditText edtPassword;
    private ProgressBar progressBar;
    private ILoginPresenter iLoginPresenter;


    @Override
    protected int getResourceID() {
        return R.layout.activity_login;
    }

    @Override
    protected void bindView() {
        btnLogin =  findViewById(R.id.btn_login);
        txtInputAccount =  findViewById(R.id.txt_input_account);
        txtInputPassword =  findViewById(R.id.txt_input_password);
        edtAccount =  findViewById(R.id.edt_account);
        edtPassword =  findViewById(R.id.edt_password);
        progressBar = findViewById(R.id.progressBar);

        iLoginPresenter = new LoginPresenter(this);


    }

    @Override
    protected void viewClickListener() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                iLoginPresenter.login(InputHelper.toString(edtAccount), InputHelper.toString(edtPassword));
                break;
        }
    }

    @Override
    public void onEmptyAccount(boolean isEmpty) {
        txtInputAccount.setError(isEmpty ? getString(R.string.login_account_not_empty) : null);
    }

    @Override
    public void onEmptyPassword(boolean isEmpty) {
        txtInputPassword.setError(isEmpty ? getString(R.string.login_password_not_empty) : null);
    }

    @Override
    public void loginSuccess(LoginUser loginUser) {
        Toasty.normal(this, "Hello " + loginUser.getName()).show();
    }

    @Override
    public void loginFail(String errorMessage) {
        Toasty.error(this, errorMessage).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        btnLogin.setVisibility(View.VISIBLE);
    }
}
