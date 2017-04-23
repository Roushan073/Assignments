package com.roushan.assignment2;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;
    public static final String IS_USER_LOGGED_IN = "isUserLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_USER_ID = "user_id";


    public Session(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("my_app", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedIn(boolean loggedIn) {
        editor.putBoolean(IS_USER_LOGGED_IN, loggedIn);
        editor.commit();
    }

    public void setUserDetails(String name, String user_id) {
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_USER_ID, user_id);
        editor.commit();
    }

    public boolean checkLogin() {
        return prefs.getBoolean(IS_USER_LOGGED_IN, false);
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_NAME, prefs.getString(KEY_NAME, null));
        user.put(KEY_USER_ID, prefs.getString(KEY_USER_ID, null));

        return user;
    }
}
