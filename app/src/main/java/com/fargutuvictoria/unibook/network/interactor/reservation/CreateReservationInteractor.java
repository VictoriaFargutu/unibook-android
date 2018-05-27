package com.fargutuvictoria.unibook.network.interactor.reservation;

import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.Reservation;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

public interface CreateReservationInteractor extends Interactor {
    interface Callback {
        void onCreateReservationsSuccess(Reservation reservation);

        void onCreateReservationsError(ExceptionInfo exceptionInfo);
    }

    void initiate(Reservation reservation, Callback callback);
}
