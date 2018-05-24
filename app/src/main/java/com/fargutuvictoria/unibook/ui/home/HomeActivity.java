package com.fargutuvictoria.unibook.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.commons.ToFilterFrom;
import com.fargutuvictoria.unibook.ui.home.adapter.HomeFragmentPagerAdapter;
import com.fargutuvictoria.unibook.ui.reservation.ReservationActivity;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.home_view_pager);

        // Create an adapter that knows which fragment should be shown on each page
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        tabLayout = findViewById(R.id.home_sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //set item as selected to persist highlight
        item.setChecked(true);
        // close drawer when item is tapped
        drawerLayout.closeDrawers();

        // Add code here to update the UI based on the item selected
        switch (item.getItemId()) {
            case R.id.reservation:
                Intent intent = new Intent(HomeActivity.this, ReservationActivity.class);
                intent.putExtra("toFilterFrom", ToFilterFrom.FROM_HOME);
                startActivity(intent);
                return true;
            case R.id.notification:
                return true;
            case R.id.profile:
                return true;
            case R.id.log_out:
                return true;
            default:
                drawerLayout.closeDrawer(GravityCompat.START);
        }
        return false;
    }
}
