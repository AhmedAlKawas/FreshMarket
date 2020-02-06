package com.example.freshmarket.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    private static final String PREF_NAME = "prefSettings";
    private static final String Is_First_LAUNCH = "Is_First_LAUNCH";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public PrefManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }

    public Boolean Is_First_LAUNCH() {
        return pref.getBoolean(Is_First_LAUNCH, true);
    }

    public void stop_First_LAUNCH() {
        editor.putBoolean(Is_First_LAUNCH, false);
        editor.commit();
    }

}
