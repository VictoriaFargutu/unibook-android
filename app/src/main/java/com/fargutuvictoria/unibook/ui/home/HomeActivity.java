package com.fargutuvictoria.unibook.ui.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.ui.home.adapter.HomeFragmentPagerAdapter;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    @Inject
    HomeContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        android.support.v7.widget.Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setContentView(R.layout.activity_home);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.home_view_pager);

        // Create an adapter that knows which fragment should be shown on each page
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.home_sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
