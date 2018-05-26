package com.fargutuvictoria.unibook.ui.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.commons.TabConstants;
import com.fargutuvictoria.unibook.ui.filter.ReservationFilterActivity;
import com.fargutuvictoria.unibook.ui.home.HomeActivity;
import com.fargutuvictoria.unibook.ui.reservation.adapter.ReservationFragmentPagerAdapter;

public class ReservationActivity extends AppCompatActivity {
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Toolbar toolbar = findViewById(R.id.reservations_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.reservations_view_pager);

        // Create an adapter that knows which fragment should be shown on each page
        ReservationFragmentPagerAdapter adapter = new ReservationFragmentPagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        tabLayout = findViewById(R.id.reservations_sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.getTabAt(TabConstants.MAKE_RESERVATION_TAB_POSITION).select();
        final Button filter_button = findViewById(R.id.filters_button);
        final LinearLayout reservationQuickView = findViewById(R.id.reservation_quick_view);

        Intent intent = getIntent();
        final String fromFilter = (String) intent.getSerializableExtra("toFilterFrom");
        final Classroom classroom = (Classroom) intent.getSerializableExtra("classroom");

        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReservationFilterActivity.class);
                intent.putExtra("toFilterFrom", fromFilter);
                intent.putExtra("classroom", classroom);
                startActivity(intent);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == TabConstants.MY_RESERVATIONS_TAB_POSITION) {
                    filter_button.setVisibility(View.GONE);
                }
                if (tab.getPosition() == TabConstants.MAKE_RESERVATION_TAB_POSITION) {
                    filter_button.setVisibility(View.VISIBLE);
                    reservationQuickView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
