package com.fargutuvictoria.unibook.ui.reservation.show;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fargutuvictoria.commons.model.Reservation;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.ui.home.adapter.reservation.ReservationListViewAdapter;

import java.util.List;

public class MyReservationsFragment extends Fragment implements MyReservationsContract.Fragment {
    private MyReservationsPresenter myReservationsPresenter;
    private RecyclerView reservationsRecycler;

    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myReservationsPresenter = new MyReservationsPresenter(this);
        reservationsRecycler = view.findViewById(R.id.recycler_view);
        myReservationsPresenter.loadReservations();
    }

    @Override
    public void showReservationsLoaded(List<Reservation> reservations) {
        RecyclerView.Adapter recylerAdapter = new ReservationListViewAdapter(reservations);
        reservationsRecycler.setAdapter(recylerAdapter);
        reservationsRecycler.setLayoutManager(new LinearLayoutManager(UnibookApplication.getInstance()));
    }
}
