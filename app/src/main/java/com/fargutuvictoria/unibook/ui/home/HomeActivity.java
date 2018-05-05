package com.fargutuvictoria.unibook.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fargutuvictoria.commons.Constants;
import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.ui.home.adapter.ClassroomListViewAdapter;
import com.fargutuvictoria.unibook.ui.home.adapter.HomeFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    @Inject
    HomeContract.Presenter presenter;
    private RecyclerView classroomsRecycler;
    private RecyclerView.Adapter recylerAdapter;


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

    public void broadcastIntent(List<Classroom> classrooms) {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_CLASSROOMS_LOADED);
        intent.putExtra(Constants.EXTRA_CLASSROOMS, new ArrayList<>(classrooms));
        sendBroadcast(intent);
    }

    @Override
    public void showClassroomsLoaded(List<Classroom> classrooms) {
        classroomsRecycler = findViewById(R.id.courses_recycler_view);
        recylerAdapter = new ClassroomListViewAdapter(classrooms, presenter);
        classroomsRecycler.setAdapter(recylerAdapter);
        classroomsRecycler.setLayoutManager(new LinearLayoutManager(this));
        broadcastIntent(classrooms);
    }
}
