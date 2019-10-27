package com.example.instagramclone_android.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private final String SHARED_PREF = "sharedPref";
    private final String IS_LOGIN = "isLogin";

    public static SharedPrefs sharedPrefs = new SharedPrefs();


    private SharedPreferences getSharedPrefs(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public void setLoginStatus(boolean setBool,Context context) {
        SharedPreferences.Editor editor = getSharedPrefs(context).edit();
        editor.putBoolean(IS_LOGIN,setBool);
        editor.apply();
    }

    public boolean getLoginStatus(Context context) {
        return getSharedPrefs(context).getBoolean(IS_LOGIN,false);
    }
}
