package by.vshkl.android.imagequiz.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import by.vshkl.android.imagequiz.R;

public class SharedPrefUtils {

    public static void setLogged(Context context, boolean isLogged) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putBoolean(context.getString(R.string.pref_logged_name), isLogged).apply();
    }

    public static boolean getLogged(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(context.getString(R.string.pref_logged_name), false);
    }
}
