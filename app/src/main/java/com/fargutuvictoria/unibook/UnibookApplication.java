package com.fargutuvictoria.unibook;

import android.app.Application;

import com.fargutuvictoria.unibook.di.component.AppComponent;
import com.fargutuvictoria.unibook.di.component.DaggerAppComponent;
import com.fargutuvictoria.unibook.di.module.ApplicationModule;
import com.fargutuvictoria.unibook.di.module.NetworkModule;

/**
 * Created by fargutuvictoria on 07/03/2018.
 */

public class UnibookApplication extends Application {
    private static UnibookApplication INSTANCE;

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        component = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public static UnibookApplication getInstance() {
        return INSTANCE;
    }

    public AppComponent getComponent() {
        return component;
    }
}
