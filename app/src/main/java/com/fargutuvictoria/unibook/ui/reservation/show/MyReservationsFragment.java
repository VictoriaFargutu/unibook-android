package com.fargutuvictoria.unibook.ui.reservation.show;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fargutuvictoria.commons.model.Reservation;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.ui.reservation.adapter.ReservationListViewAdapter;

import java.util.Calendar;
import java.util.List;

public class MyReservationsFragment extends Fragment implements MyReservationsContract.Fragment {
    private MyReservationsPresenter myReservationsPresenter;
    private RecyclerView reservationsRecycler;
    private LinearLayout reservationQuickView;

    private TextView reservationQuickClassroom;
    private TextView reservationQuickDay;
    private TextView reservationQuickWeekType;
    private TextView reservationQuickCourse;
    private TextView reservationQuickHour;
    private TextView reservationQuickDate;
    private TextView reservationQuickStudentsGroup;
    private TextView reservationQuickStudentsYear;
    private Button cancelReservationButton;
    private Button closeQuickViewButton;

    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reservationQuickView = getActivity().findViewById(R.id.reservation_quick_view);
        reservationQuickClassroom = reservationQuickView.findViewById(R.id.reservation_quick_classroom);
        reservationQuickDay = reservationQuickView.findViewById(R.id.reservation_quick_day);
        reservationQuickWeekType = reservationQuickView.findViewById(R.id.reservation_week_type);
        reservationQuickCourse = reservationQuickView.findViewById(R.id.reservation_quick_course);
        reservationQuickHour = reservationQuickView.findViewById(R.id.reservation_quick_hour);
        reservationQuickDate = reservationQuickView.findViewById(R.id.reservation_quick_date);
        reservationQuickStudentsGroup = reservationQuickView.findViewById(R.id.reservation_quick_students_group);
        reservationQuickStudentsYear = reservationQuickView.findViewById(R.id.reservation_quick_students_year);
        cancelReservationButton = reservationQuickView.findViewById(R.id.quick_cancel_reservation);
        closeQuickViewButton = reservationQuickView.findViewById(R.id.close_button);

        myReservationsPresenter = new MyReservationsPresenter(this);
        reservationsRecycler = view.findViewById(R.id.recycler_view);
        myReservationsPresenter.loadReservations();
    }

    @Override
    public void showReservationsLoaded(List<Reservation> reservations) {
        RecyclerView.Adapter recylerAdapter = new ReservationListViewAdapter(reservations, this);
        reservationsRecycler.setAdapter(recylerAdapter);
        reservationsRecycler.setLayoutManager(new LinearLayoutManager(UnibookApplication.getInstance()));
    }

    @Override
    public void openReservationQuickView(final Reservation reservation) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reservation.getDate());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String date = "" + day + " " + month + " " + year;
        reservationQuickClassroom.setText(reservation.getClassroom().getName());
        reservationQuickDate.setText(date);
        reservationQuickHour.setText(reservation.getHour());
        reservationQuickDay.setText(reservation.getDay());
        reservationQuickWeekType.setText(reservation.getWeekType());
        reservationQuickCourse.setText(reservation.getCourse().getName());
        reservationQuickStudentsGroup.setText(reservation.getStudentsGroup().getName());
        reservationQuickStudentsYear.setText("Year: " + reservation.getStudentsGroup().getYear());
        reservationQuickView.setVisibility(View.VISIBLE);

        cancelReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myReservationsPresenter.cancelReservation(reservation.getId());
            }
        });

        closeQuickViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reservationQuickView.setVisibility(View.GONE);
            }
        });
    }
}
