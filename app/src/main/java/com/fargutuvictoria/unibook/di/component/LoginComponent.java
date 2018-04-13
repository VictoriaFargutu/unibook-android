package com.fargutuvictoria.unibook.di.component;

import com.fargutuvictoria.unibook.di.module.LoginModule;
import com.fargutuvictoria.unibook.di.scopes.ActivityScope;
import com.fargutuvictoria.unibook.ui.login.LoginActivity;
import com.fargutuvictoria.unibook.ui.login.LoginContract;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = LoginModule.class)
@ActivityScope
public interface LoginComponent {

    LoginContract.Presenter getPresenter();

    void inject(LoginActivity activity);

}
