package com.fargutuvictoria.unibook.ui.home.classroom.seminar;

import android.content.Intent;
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
import com.fargutuvictoria.unibook.commons.ToFilterFrom;
import com.fargutuvictoria.unibook.ui.home.adapter.classroom.ClassroomListViewAdapter;
import com.fargutuvictoria.unibook.ui.reservation.ReservationActivity;

import java.util.List;

public class SeminarRoomFragment extends Fragment implements SeminarRoomContract.Fragment {
    private SeminarRoomPresenter seminarRoomPresenter;
    private RecyclerView classroomsRecycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seminarRoomPresenter = new SeminarRoomPresenter(this);
        classroomsRecycler = view.findViewById(R.id.recycler_view);
        seminarRoomPresenter.loadSeminarRooms();
    }

    @Override
    public void showSeminarRoomsLoaded(List<Classroom> seminarRooms) {
        RecyclerView.Adapter recylerAdapter = new ClassroomListViewAdapter(seminarRooms, seminarRoomPresenter);
        classroomsRecycler.setAdapter(recylerAdapter);
        classroomsRecycler.setLayoutManager(new LinearLayoutManager(UnibookApplication.getInstance()));
    }

    @Override
    public void openReservationActivity(Classroom classroom) {
        Intent intent = new Intent(this.getActivity(), ReservationActivity.class);
        intent.putExtra("classroom", classroom);
        intent.putExtra("toFilterFrom", ToFilterFrom.FROM_CLASSROOM);
        startActivity(intent);
    }
}
