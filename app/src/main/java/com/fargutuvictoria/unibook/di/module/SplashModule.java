package com.fargutuvictoria.unibook.di.module;

import com.fargutuvictoria.api.retrofit.service.AuthService;
import com.fargutuvictoria.commons.preferences.SharedPreferencesHandler;
import com.fargutuvictoria.unibook.di.scopes.ActivityScope;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;
import com.fargutuvictoria.unibook.network.interactor.session.validation.SessionValidationInteractor;
import com.fargutuvictoria.unibook.network.interactor.session.validation.SessionValidationInteractorImpl;
import com.fargutuvictoria.unibook.ui.splash.SplashContract;
import com.fargutuvictoria.unibook.ui.splash.SplashPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fargutuvictoria on 27/02/2018.
 */

@Module
public class SplashModule {

    private SplashContract.View view;

    public SplashModule(SplashContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public SessionValidationInteractor provideInteractor(MainThread mainThread,
                                                         InteractorExecutor interactorExecutor,
                                                         AuthService authService) {
        return new SessionValidationInteractorImpl(mainThread, interactorExecutor, authService);
    }

    @ActivityScope
    @Provides
    public SplashContract.Presenter providePresenter(SharedPreferencesHandler sharedPreferencesHandler,
                                                     SessionValidationInteractor sessionValidationInteractor) {
        return new SplashPresenter(sharedPreferencesHandler, view, sessionValidationInteractor);
    }
}
