package com.mvplogin.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * 功能
 */

public class InputHelper {

    public static final String SPACE = "\u202F\u202F";

    private static boolean isWhiteSpaces(@Nullable String s) {
        return s != null && s.matches("\\s+");
    }

    public static boolean isEmpty(@Nullable String text) {
        return text == null || TextUtils.isEmpty(text) || isWhiteSpaces(text) || text.equalsIgnoreCase("null");
    }

    public static String toString(@NonNull EditText editText) {
        return editText.getText().toString();
    }

    public static String toString(@NonNull TextInputEditText editText) {
        return editText.getText().toString();
    }
}
