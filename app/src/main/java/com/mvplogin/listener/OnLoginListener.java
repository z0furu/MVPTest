package com.mvplogin.listener;

import com.mvplogin.model.LoginUser;

/**
 * 功能
 */

public interface OnLoginListener {
    void onSuccess(LoginUser loginUser);
    void onError(String errorMessage);
}

