package com.fargutuvictoria.unibook.di.module;

import android.app.Application;
import android.content.Context;

import com.fargutuvictoria.commons.preferences.SharedPreferencesHandler;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fargutuvictoria on 07/03/2018.
 */
@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Application context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public SharedPreferencesHandler provideSharedPreferencesHandler(Context context) {
        return new SharedPreferencesHandler(context);
    }

    @Provides
    @Singleton
    public MainThread provideMainThread() {
        return new MainThread();
    }

    @Provides
    @Singleton
    public InteractorExecutor provideExecutor() {
        return new InteractorExecutor();
    }
}
