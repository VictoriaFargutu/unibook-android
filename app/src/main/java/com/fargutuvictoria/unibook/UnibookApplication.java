package com.fargutuvictoria.unibook;

import android.app.Application;

/**
 * Created by fargutuvictoria on 07/03/2018.
 */

public class UnibookApplication extends Application {
    private static UnibookApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

    }

    public static UnibookApplication getInstance() {
        return INSTANCE;
    }

}
