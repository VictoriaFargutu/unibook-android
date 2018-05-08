package com.fargutuvictoria.unibook.ui.home.adapter.reservation.freeoptions;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fargutuvictoria.commons.model.Reservation;
import com.fargutuvictoria.unibook.R;

import java.util.List;

public class FreeOptionsCardViewAdapter extends RecyclerView.Adapter<FreeOptionsCardViewAdapter.FreeOptionsViewHolder> {
    private List<Reservation> reservations;

    public FreeOptionsCardViewAdapter(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public FreeOptionsCardViewAdapter.FreeOptionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.make_reservation_item_layout, parent, false);
        return new FreeOptionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FreeOptionsCardViewAdapter.FreeOptionsViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);
        holder.bind(reservation);
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public class FreeOptionsViewHolder extends RecyclerView.ViewHolder {
        private final TextView freeOptionClassroom;
        private Reservation reservation;

        public FreeOptionsViewHolder(View itemView) {
            super(itemView);
            freeOptionClassroom = itemView.findViewById(R.id.free_reservation_classroom);
        }

        private void bind(Reservation reservation) {
            //   reservationClassroom.setText(reservation.getClassroom().getName());
//            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//            Date date = null;
//            try {
//                date = format.parse(reservation.getDate());
//                System.out.println(date);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
            // Sat Jan 02 00:00:00 GMT 2010
            freeOptionClassroom.setText(reservation.getClassroom().getName());

            this.reservation = reservation;
        }
    }
}
