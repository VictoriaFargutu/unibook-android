package com.fargutuvictoria.commons.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mihai on 2/19/2018.
 */

public class SharedPreferencesHandler {

    private SharedPreferences sharedPreferences;

    public SharedPreferencesHandler(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PrefConstants.PREF_NAME_TAG, Context.MODE_PRIVATE);
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
