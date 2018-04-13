package com.fargutuvictoria.unibook.di.component;

import com.fargutuvictoria.api.retrofit.service.AuthService;
import com.fargutuvictoria.commons.preferences.SharedPreferencesHandler;
import com.fargutuvictoria.unibook.di.module.ApplicationModule;
import com.fargutuvictoria.unibook.di.module.NetworkModule;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fargutuvictoria on 07/03/2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface AppComponent {

    AuthService getService();

    MainThread getMainThread();

    InteractorExecutor getInteractorExecutor();

    SharedPreferencesHandler getSharedPrefHelper();
}
