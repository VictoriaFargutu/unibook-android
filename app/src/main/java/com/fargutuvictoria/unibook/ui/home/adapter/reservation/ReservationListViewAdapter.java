package com.fargutuvictoria.unibook.ui.home.adapter.reservation;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fargutuvictoria.commons.model.Reservation;
import com.fargutuvictoria.unibook.R;

import java.util.List;

public class ReservationListViewAdapter extends RecyclerView.Adapter<ReservationListViewAdapter.ReservationViewHolder> {
    private List<Reservation> reservations;

    public ReservationListViewAdapter(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item_layout, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);
        holder.bind(reservation);
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public class ReservationViewHolder extends RecyclerView.ViewHolder {
      //  private final TextView reservationClassroom;
        private final TextView reservationDate;
        //        private final TextView reservationDay;
//        private final TextView reservationCourse;
//        private final TextView reservationHour;
//        private final Button cancelReservationButton;
        private Reservation reservation;

        public ReservationViewHolder(View itemView) {
            super(itemView);
          //  reservationClassroom = itemView.findViewById(R.id.reservation_classroom);
            reservationDate = itemView.findViewById(R.id.reservation_date);
//            reservationDay = itemView.findViewById(R.id.reservation_day);
//            reservationCourse = itemView.findViewById(R.id.reservation_course);
//            reservationHour = itemView.findViewById(R.id.reservation_hour);
        }

        private void bind(Reservation reservation) {
         //   reservationClassroom.setText(reservation.getClassroom().getName());
            reservationDate.setText("" + reservation.getDate());

            this.reservation = reservation;
        }
    }
}
