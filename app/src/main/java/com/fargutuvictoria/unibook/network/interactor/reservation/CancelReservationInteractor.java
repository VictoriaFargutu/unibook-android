package com.fargutuvictoria.unibook.network.interactor.reservation;


import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

public interface CancelReservationInteractor extends Interactor {
    interface Callback {
        void onCancelReservationsSuccess();

        void onCancelReservationsError(ExceptionInfo exceptionInfo);
    }

    void initiate(Long reservationId, Callback callback);
}
