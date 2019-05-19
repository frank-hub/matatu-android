package com.example.frankline.myclinic;

import android.content.Context;
import android.content.SharedPreferences;

public class Cache {

    private static final String APP_SETTINGS = "APP_SETTINGS";
    private static Cache ourInstance = new Cache();
    private static final String AUTH_TOKEN = "com.eryxlabs.fiderides.TOKEN";
    private static final String USER_ID = "com.eryxlabs.fiderides.USER_ID";
    private static final String USER_NAME = "com.eryxlabs.fiderides.USER_NAME";


    private Cache() {}

    public static Cache is() {
        return ourInstance;
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getApplicationContext().getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static String getAuthToken(Context context) {
        return getSharedPreferences(context).getString(AUTH_TOKEN , "");
    }

    public static void setAuthToken(Context context, String newValue) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(AUTH_TOKEN , newValue);
        editor.apply();
    }

    public static boolean hasAuthToken(Context context) {
        return getSharedPreferences(context).contains(AUTH_TOKEN);
    }

    public static void removeAuthToken(Context context) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(AUTH_TOKEN);
        editor.apply();
    }

    public static int getUserId(Context context) {
        return getSharedPreferences(context).getInt(USER_ID, 0);
    }

    public static void setUserId(Context context, int newValue) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(USER_ID , newValue);
        editor.apply();
    }

    public static String getUserName(Context context) {
        return getSharedPreferences(context).getString(USER_NAME, null);
    }

    public static void setUserName(Context context, String newValue) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(USER_NAME, newValue);
        editor.apply();
    }

    public static void setAuthDetails(Context context, String token, int userId, String userName) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(AUTH_TOKEN, token);
        editor.putInt(USER_ID, userId);
        editor.putString(USER_NAME, userName);
        editor.apply();
    }
}
