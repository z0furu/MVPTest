package com.mvplogin;

import com.mvplogin.module.LoginUser;

/**
 * 功能
 */

public interface OnLoginListener {
    void onSuccess(LoginUser loginUser);
    void onError(String errorMessage);
}

