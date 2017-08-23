package com.mvplogin.view;

import com.mvplogin.module.LoginUser;

/**
 * 功能
 */

public interface ILoginView {
    void onEmptyAccount(boolean isEmpty);
    void onEmptyPassword(boolean isEmpty);
    void loginSuccess(LoginUser loginUser);
    void loginFail(String errorMessage);
    void showProgress();
    void hideProgress();

}
