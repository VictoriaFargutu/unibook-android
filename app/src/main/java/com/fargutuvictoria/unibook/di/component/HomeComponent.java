package com.fargutuvictoria.unibook.di.component;

import com.fargutuvictoria.unibook.di.module.HomeModule;
import com.fargutuvictoria.unibook.di.scopes.ActivityScope;
import com.fargutuvictoria.unibook.ui.home.HomeActivity;
import com.fargutuvictoria.unibook.ui.home.HomeContract;

import dagger.Component;

/**
 * Created by fargutuvictoria on 02/03/2018.
 */

@Component(dependencies = AppComponent.class, modules = HomeModule.class)
@ActivityScope
public interface HomeComponent {
    HomeContract.Presenter getPresenter();

    void inject(HomeActivity homeActivity);
}
