package com.fargutuvictoria.unibook.ui.reservation;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.fargutuvictoria.commons.DateDeserializer;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.ui.home.adapter.reservation.ReservationFragmentPagerAdapter;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class ReservationActivity extends AppCompatActivity {
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar myToolbar = findViewById(R.id.reservations_toolbar);
        setSupportActionBar(myToolbar);
        setContentView(R.layout.activity_reservation);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.reservations_view_pager);

        // Create an adapter that knows which fragment should be shown on each page
        ReservationFragmentPagerAdapter adapter = new ReservationFragmentPagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        tabLayout = findViewById(R.id.reservations_sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
