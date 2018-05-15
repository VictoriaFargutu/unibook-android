package com.fargutuvictoria.unibook.ui.reservation.show;


import android.widget.Toast;

import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.Reservation;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.network.interactor.reservation.CancelReservationInteractor;
import com.fargutuvictoria.unibook.network.interactor.reservation.CancelReservationInteractorImpl;
import com.fargutuvictoria.unibook.network.interactor.reservation.GetMyReservationsInteractor;
import com.fargutuvictoria.unibook.network.interactor.reservation.GetMyReservationsInteractorImpl;

import java.util.List;

public class MyReservationsPresenter implements MyReservationsContract.Presenter, GetMyReservationsInteractor.Callback, CancelReservationInteractor.Callback {
    private MyReservationsContract.Fragment fragment;

    public MyReservationsPresenter(MyReservationsContract.Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void loadReservations() {
        GetMyReservationsInteractor getMyReservationsInteractor = new GetMyReservationsInteractorImpl();
        getMyReservationsInteractor.initiate(this);
    }

    @Override
    public void onLoadReservationsSuccess(List<Reservation> reservations) {
        fragment.showReservationsLoaded(reservations);
    }

    @Override
    public void onLoadReservationsError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void cancelReservation(Long reservationId) {
        CancelReservationInteractor cancelReservationInteractor = new CancelReservationInteractorImpl();
        cancelReservationInteractor.initiate(reservationId, this);
    }

    @Override
    public void onCancelReservationsSuccess() {
        Toast.makeText(UnibookApplication.getInstance(), "Reservation canceled!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCancelReservationsError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }
}
