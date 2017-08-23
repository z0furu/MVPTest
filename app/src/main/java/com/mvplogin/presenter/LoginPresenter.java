package com.mvplogin.presenter;

import com.mvplogin.api.ApiClient;
import com.mvplogin.listener.OnLoginListener;
import com.mvplogin.module.LoginUser;
import com.mvplogin.utils.InputHelper;
import com.mvplogin.view.ILoginView;

/**
 * 功能
 */

public class LoginPresenter implements ILoginPresenter , OnLoginListener{

    private ILoginView iLoginView;
    private ApiClient apiClient;

    public LoginPresenter(ILoginView iLoginView) {
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
