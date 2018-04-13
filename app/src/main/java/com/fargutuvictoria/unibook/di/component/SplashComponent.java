package com.fargutuvictoria.unibook.di.component;

import com.fargutuvictoria.unibook.di.module.SplashModule;
import com.fargutuvictoria.unibook.di.scopes.ActivityScope;
import com.fargutuvictoria.unibook.network.interactor.session.validation.SessionValidationInteractor;
import com.fargutuvictoria.unibook.ui.splash.SplashActivity;
import com.fargutuvictoria.unibook.ui.splash.SplashContract;

import dagger.Component;

/**
 * Created by fargutuvictoria on 27/02/2018.
 */

@Component(dependencies = AppComponent.class, modules = SplashModule.class)
@ActivityScope
public interface SplashComponent {
    SplashContract.Presenter getPresenter();

    SessionValidationInteractor getSessionValidationInteractor();

    void inject(SplashActivity splashActivity);
}
