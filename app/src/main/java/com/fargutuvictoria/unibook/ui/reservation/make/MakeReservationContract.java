package com.fargutuvictoria.unibook.ui.reservation.make;


import com.fargutuvictoria.commons.model.Reservation;

import java.util.List;

public interface MakeReservationContract {
    interface Fragment {
        void showOptionsLoaded(List<Reservation> reservations);
    }

    interface Presenter {
        void loadFreeOptions();
    }
}
