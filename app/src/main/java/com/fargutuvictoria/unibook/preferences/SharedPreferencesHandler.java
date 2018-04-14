package com.fargutuvictoria.unibook.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.fargutuvictoria.unibook.UnibookApplication;

public class SharedPreferencesHandler {
    private static SharedPreferencesHandler instance;

    private SharedPreferences sharedPreferences;

    private SharedPreferencesHandler(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public static SharedPreferencesHandler getInstance() {
        if (instance == null) {
            instance = new SharedPreferencesHandler(getPrefs());
        }
        return instance;
    }

    private static SharedPreferences getPrefs() {
        return UnibookApplication.getInstance().getSharedPreferences(PrefConstants.PREF_NAME_TAG, Context.MODE_PRIVATE);
    }

    public String getSessionToken() {
        return sharedPreferences.getString(PrefConstants.SESSION, "");
    }

    public Boolean saveSessionToken(String session) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PrefConstants.SESSION, session);
        return editor.commit();
    }

}
