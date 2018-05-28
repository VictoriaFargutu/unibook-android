package com.fargutuvictoria.unibook.ui.reservation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fargutuvictoria.commons.model.Reservation;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.ui.reservation.show.MyReservationsContract;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ReservationListViewAdapter extends RecyclerView.Adapter<ReservationListViewAdapter.ReservationViewHolder> {
    private List<Reservation> reservations;
    private MyReservationsContract.Fragment fragment;

    public ReservationListViewAdapter(List<Reservation> reservations, MyReservationsContract.Fragment fragment) {
        this.reservations = reservations;
        this.fragment = fragment;
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
        private final TextView reservationDate;
        private final TextView reservationClassroom;
        private Reservation reservation;

        public ReservationViewHolder(View itemView) {
            super(itemView);
            reservationDate = itemView.findViewById(R.id.reservation_date);
            reservationClassroom = itemView.findViewById(R.id.reservation_classroom);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment.openReservationQuickView(reservation);
                }
            });

        }

        private void bind(Reservation reservation) {
            if (reservation.getClassroom() != null) {
                reservationClassroom.setText(reservation.getClassroom().getName());
            }
            String myFormat = "dd/MM/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            reservationDate.setText(sdf.format(reservation.getDate()));

            this.reservation = reservation;
        }
    }
}
