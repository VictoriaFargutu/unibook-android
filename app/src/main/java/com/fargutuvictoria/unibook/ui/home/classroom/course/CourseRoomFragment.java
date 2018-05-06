package com.fargutuvictoria.unibook.ui.home.classroom.course;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.ui.home.adapter.ClassroomListViewAdapter;

import java.util.List;

public class CourseRoomFragment extends android.support.v4.app.Fragment implements CourseRoomContract.Fragment {
    private CourseRoomPresenter courseRoomPresenter;
    private RecyclerView classroomsRecycler;

    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        courseRoomPresenter = new CourseRoomPresenter(this);
        classroomsRecycler = view.findViewById(R.id.courses_recycler_view);
        courseRoomPresenter.loadCourseRooms();
    }

    @Override
    public void showCourseRoomsLoaded(List<Classroom> classrooms) {
        RecyclerView.Adapter recylerAdapter = new ClassroomListViewAdapter(classrooms, courseRoomPresenter);
        classroomsRecycler.setAdapter(recylerAdapter);
        classroomsRecycler.setLayoutManager(new LinearLayoutManager(UnibookApplication.getInstance()));
    }
}
