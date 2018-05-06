package com.fargutuvictoria.unibook.ui.reservation.show;


import com.fargutuvictoria.commons.model.Reservation;

import java.util.List;

public interface MyReservationsContract {
    interface Fragment {
        void showReservationsLoaded(List<Reservation> reservations);
    }

    interface Presenter {
        void loadReservations();
    }
}
