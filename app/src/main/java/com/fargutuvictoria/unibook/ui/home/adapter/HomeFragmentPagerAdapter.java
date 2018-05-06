package com.fargutuvictoria.unibook.ui.home.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.commons.TabConstants;
import com.fargutuvictoria.unibook.ui.home.classroom.course.CourseRoomFragment;
import com.fargutuvictoria.unibook.ui.home.classroom.lab.LabRoomFragment;
import com.fargutuvictoria.unibook.ui.home.classroom.seminar.SeminarRoomFragment;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public HomeFragmentPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == TabConstants.HOME_COURSE_TAB_POSITION) {
            return new CourseRoomFragment();
        } else if (position == TabConstants.HOME_LAB_TAB_POSITION) {
            return new LabRoomFragment();
        } else {
            return new SeminarRoomFragment();
        }
    }

    @Override
    public int getCount() {
        return TabConstants.HOME_TABS_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case TabConstants.HOME_COURSE_TAB_POSITION:
                return context.getString(R.string.tab_home_course_room);
            case TabConstants.HOME_LAB_TAB_POSITION:
                return context.getString(R.string.tab_home_lab_room);
            case TabConstants.HOME_SEM_TAB_POSITION:
                return context.getString(R.string.tab_home_sem_room);
            default:
                return null;
        }
    }
}
