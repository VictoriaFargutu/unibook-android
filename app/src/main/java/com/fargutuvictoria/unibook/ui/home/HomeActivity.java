package com.fargutuvictoria.unibook.ui.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fargutuvictoria.unibook.R;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    @Inject
    HomeContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
    }
}
