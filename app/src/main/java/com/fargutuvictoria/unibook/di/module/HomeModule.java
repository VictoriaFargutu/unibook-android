package com.fargutuvictoria.unibook.di.module;

import com.fargutuvictoria.commons.preferences.SharedPreferencesHandler;
import com.fargutuvictoria.unibook.di.scopes.ActivityScope;
import com.fargutuvictoria.unibook.ui.home.HomeContract;
import com.fargutuvictoria.unibook.ui.home.HomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fargutuvictoria on 02/03/2018.
 */

@Module
public class HomeModule {
    private HomeContract.View view;

    public HomeModule(HomeContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public HomeContract.Presenter providePresenter(SharedPreferencesHandler sharedPreferencesHandler) {
        return new HomePresenter(sharedPreferencesHandler, view);
    }

}
