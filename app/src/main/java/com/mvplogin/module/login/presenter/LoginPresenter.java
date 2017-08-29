package com.mvplogin.module.login.presenter;

import com.mvplogin.api.ApiClient;
import com.mvplogin.listener.OnLoginListener;
import com.mvplogin.model.LoginUser;
import com.mvplogin.module.login.LoginConstact;
import com.mvplogin.utils.InputHelper;

/**
 * 功能
 */

public class LoginPresenter implements LoginConstact.Presenter, OnLoginListener{

    private LoginConstact.View iLoginView;
    private ApiClient apiClient;

    public LoginPresenter(LoginConstact.View iLoginView) {
        this.iLoginView = iLoginView;
        apiClient = ApiClient.getInstance();
    }

    @Override
    public void login(String account, String password) {
        boolean accountIsEmpty = InputHelper.isEmpty(account);
        boolean passwordIsEmpty = InputHelper.isEmpty(password);
        iLoginView.onEmptyAccount(accountIsEmpty);
        iLoginView.onEmptyPassword(passwordIsEmpty);

        if (!accountIsEmpty && !passwordIsEmpty) {
            iLoginView.showProgress();
            apiClient.login(this, account, password);
        }
    }

    @Override
    public void onSuccess(LoginUser loginUser) {
        iLoginView.hideProgress();
        iLoginView.loginSuccess(loginUser);
    }

    @Override
    public void onError(String errorMessage) {
        iLoginView.hideProgress();
        iLoginView.loginFail(errorMessage);
    }
}
