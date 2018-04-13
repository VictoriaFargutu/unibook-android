package com.fargutuvictoria.unibook.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.di.component.DaggerSplashComponent;
import com.fargutuvictoria.unibook.di.module.SplashModule;
import com.fargutuvictoria.unibook.ui.home.HomeActivity;
import com.fargutuvictoria.unibook.ui.login.LoginActivity;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    @Inject
    SplashContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSplashComponent.builder()
                .appComponent(UnibookApplication.getInstance().getComponent())
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);

        setContentView(R.layout.activity_splash);
        if (!mPresenter.doSessionValidation()) {
            goToLoginActivity();
        }
    }

    @Override
    public void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
