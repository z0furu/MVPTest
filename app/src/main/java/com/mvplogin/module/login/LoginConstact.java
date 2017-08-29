package com.mvplogin.module.login;

import com.mvplogin.model.LoginUser;

/**
 * 功能
 */

public interface LoginConstact {

    interface Presenter {
        void login(String account, String password);
    }

    interface View {
        void onEmptyAccount(boolean isEmpty);
        void onEmptyPassword(boolean isEmpty);
        void loginSuccess(LoginUser loginUser);
        void loginFail(String errorMessage);
        void showProgress();
        void hideProgress();
    }
}
