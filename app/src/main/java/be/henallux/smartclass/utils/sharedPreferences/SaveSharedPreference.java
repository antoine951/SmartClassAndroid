package be.henallux.smartclass.utils.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;


public class SaveSharedPreference {

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean("logged_in_status", loggedIn);
        editor.apply();
    }

    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean("logged_in_status",false);
    }

    public static void setLoggedInUser(Context context, String token) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(token);
        editor.putString("current_user", json);
        editor.apply();
    }

    public static String getLoggedInUser(Context context) {
        return getPreferences(context).getString("current_user","");
    }

    public static void setCurrentChild(Context context, String token) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(token);
        editor.putString("current_child", json);
        editor.apply();
    }

    public static String getCurrentChild(Context context) {
        return getPreferences(context).getString("current_child","");
    }
}
