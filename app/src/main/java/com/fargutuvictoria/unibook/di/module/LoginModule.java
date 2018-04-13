package com.fargutuvictoria.unibook.di.module;

import com.fargutuvictoria.api.retrofit.service.AuthService;
import com.fargutuvictoria.commons.preferences.SharedPreferencesHandler;
import com.fargutuvictoria.unibook.di.scopes.ActivityScope;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;
import com.fargutuvictoria.unibook.network.interactor.login.LoginInteractor;
import com.fargutuvictoria.unibook.network.interactor.login.LoginInteractorImpl;
import com.fargutuvictoria.unibook.ui.login.LoginContract;
import com.fargutuvictoria.unibook.ui.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginContract.View view;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public LoginContract.Presenter providePresenter(SharedPreferencesHandler sharedPreferencesHandler,
                                                    LoginInteractor loginInteractor) {
        return new LoginPresenter(sharedPreferencesHandler, view, loginInteractor);
    }

    @ActivityScope
    @Provides
    public LoginInteractor provideInteractor(MainThread mainThread,
                                             InteractorExecutor interactorExecutor,
                                             AuthService authService) {
        return new LoginInteractorImpl(mainThread, interactorExecutor, authService);
    }
}
