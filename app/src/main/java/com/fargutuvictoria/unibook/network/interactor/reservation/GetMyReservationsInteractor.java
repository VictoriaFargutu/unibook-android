package com.fargutuvictoria.unibook.network.interactor.reservation;


import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.Reservation;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

import java.util.List;

public interface GetMyReservationsInteractor extends Interactor {
    interface Callback {
        void onLoadReservationsSuccess(List<Reservation> reservations);

        void onLoadReservationsError(ExceptionInfo exceptionInfo);
    }

    void initiate(Callback callback);
}
