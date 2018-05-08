package com.fargutuvictoria.unibook.ui.reservation.make;


import android.widget.Toast;

import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.Reservation;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.network.interactor.reservation.GetMyReservationsInteractor;
import com.fargutuvictoria.unibook.network.interactor.reservation.GetMyReservationsInteractorImpl;

import java.util.List;

public class MakeReservationPresenter implements MakeReservationContract.Presenter, GetMyReservationsInteractor.Callback {
    private MakeReservationContract.Fragment fragment;

    public MakeReservationPresenter(MakeReservationContract.Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void loadFreeOptions() {
        GetMyReservationsInteractor getMyReservationsInteractor = new GetMyReservationsInteractorImpl();
        getMyReservationsInteractor.initiate(this);
    }

    @Override
    public void onLoadReservationsSuccess(List<Reservation> reservations) {
        fragment.showOptionsLoaded(reservations);
    }

    @Override
    public void onLoadReservationsError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }
}
