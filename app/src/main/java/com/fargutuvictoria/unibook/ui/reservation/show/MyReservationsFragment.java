package com.fargutuvictoria.unibook.ui.reservation.show;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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

    private TextView noReservationsText;

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

        noReservationsText = view.findViewById(R.id.no_reservations_text_view);
        noReservationsText.setVisibility(View.GONE);

        myReservationsPresenter = new MyReservationsPresenter(this);
        reservationsRecycler = view.findViewById(R.id.recycler_view);
        myReservationsPresenter.loadReservations();
    }

    @Override
    public void showReservationsLoaded(List<Reservation> reservations) {
        RecyclerView.Adapter recylerAdapter = new ReservationListViewAdapter(reservations, this);
        reservationsRecycler.setAdapter(recylerAdapter);
        reservationsRecycler.setLayoutManager(new LinearLayoutManager(UnibookApplication.getInstance()));
        if (reservations.isEmpty()) {
            noReservationsText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void openReservationQuickView(final Reservation reservation) {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        reservationQuickDate.setText(sdf.format(reservation.getDate()));
        reservationQuickClassroom.setText(reservation.getClassroom().getName());
        reservationQuickHour.setText(reservation.getHour());
        reservationQuickDay.setText(reservation.getDay().name());
        reservationQuickWeekType.setText(reservation.getWeekType().name());
        if (reservation.getCourse() != null) {
            reservationQuickCourse.setText(reservation.getCourse().getName());
        }
        if (reservation.getStudentsGroup() != null) {
            reservationQuickStudentsGroup.setText(reservation.getStudentsGroup().getName());
            reservationQuickStudentsYear.setText("Year: " + reservation.getStudentsGroup().getYear());
        }
        reservationQuickView.setVisibility(View.VISIBLE);

        cancelReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.are_you_sure)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myReservationsPresenter.cancelReservation(reservation.getId());
                                myReservationsPresenter.loadReservations();
                                reservationQuickView.setVisibility(View.GONE);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.primaryTextColor));
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.primaryTextColor));
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
