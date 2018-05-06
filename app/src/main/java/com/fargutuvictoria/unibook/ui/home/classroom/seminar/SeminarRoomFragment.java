package com.fargutuvictoria.unibook.ui.home.classroom.seminar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.ui.home.adapter.ClassroomListViewAdapter;

import java.util.List;

/**
 * Created by fargutuvictoria on 04/05/2018.
 */

public class SeminarRoomFragment extends Fragment implements SeminarRoomContract.Fragment {
    private SeminarRoomPresenter seminarRoomPresenter;
    private RecyclerView classroomsRecycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_rooms_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seminarRoomPresenter = new SeminarRoomPresenter(this);
        classroomsRecycler = view.findViewById(R.id.courses_recycler_view);
        seminarRoomPresenter.loadSeminarRooms();
    }

    @Override
    public void showSeminarRoomsLoaded(List<Classroom> seminarRooms) {
        RecyclerView.Adapter recylerAdapter = new ClassroomListViewAdapter(seminarRooms, seminarRoomPresenter);
        classroomsRecycler.setAdapter(recylerAdapter);
        classroomsRecycler.setLayoutManager(new LinearLayoutManager(UnibookApplication.getInstance()));
    }
}
