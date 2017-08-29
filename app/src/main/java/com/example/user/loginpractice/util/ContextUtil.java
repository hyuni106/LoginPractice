package com.example.user.loginpractice.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 2017-08-29.
 */

public class ContextUtil {
    private final static String prefName = "testPref";
    //    자동로그인 여부를 저장할때 사용할 태그
    private final static String LOGIN_USER_ID = "LOGIN_USER_ID";
    private final static String LOGIN_USER_PW = "LOGIN_USER_PW";
    private final static String LOGIN_USER_NAME = "LOGIN_USER_NAME";
    private final static String LOGIN_USER_URL = "LOGIN_USER_URL";

    public static void setLoginUser(Context context, String id, String pw, String name, String url) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(LOGIN_USER_ID, id).commit();
        pref.edit().putString(LOGIN_USER_PW, pw).commit();
        pref.edit().putString(LOGIN_USER_NAME, name).commit();
        pref.edit().putString(LOGIN_USER_URL, url).commit();
    }

    public static String getLoginUserId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(LOGIN_USER_ID, "");
    }

    public static String getLoginUserPw(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(LOGIN_USER_PW, "");
    }

    public static String getLoginUserName(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(LOGIN_USER_NAME, "");
    }

    public static String getLoginUserUrl(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(LOGIN_USER_URL, "");
    }

    public static void logoutProcess(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(LOGIN_USER_ID, "").commit();
        pref.edit().putString(LOGIN_USER_PW, "").commit();
        pref.edit().putString(LOGIN_USER_NAME, "").commit();
        pref.edit().putString(LOGIN_USER_URL, "").commit();
    }
}
