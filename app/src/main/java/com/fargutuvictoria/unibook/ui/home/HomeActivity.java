package com.fargutuvictoria.unibook.ui.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.di.component.DaggerHomeComponent;
import com.fargutuvictoria.unibook.di.module.HomeModule;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    @Inject
    HomeContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        DaggerHomeComponent.builder()
                .appComponent(UnibookApplication.getInstance().getComponent())
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);

        setContentView(R.layout.activity_home);
    }
}
