package com.fargutuvictoria.unibook.ui.home.classroom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fargutuvictoria.unibook.R;

/**
 * Created by fargutuvictoria on 04/05/2018.
 */

public class CourseRoomFragment extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_rooms_list, container, false);
    }
}
