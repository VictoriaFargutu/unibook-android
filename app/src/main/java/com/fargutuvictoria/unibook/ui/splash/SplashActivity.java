package com.fargutuvictoria.unibook.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.ui.home.HomeActivity;
import com.fargutuvictoria.unibook.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        if (!new SplashPresenter(this).doSessionValidation()) {
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
