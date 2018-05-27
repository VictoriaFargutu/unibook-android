package com.fargutuvictoria.unibook.network.interactor.reservation;

import com.fargutuvictoria.api.retrofit.callback.HandledCallback;
import com.fargutuvictoria.api.retrofit.service.ReservationService;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.Reservation;
import com.fargutuvictoria.unibook.ApiClient;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;

import retrofit2.Call;

public class CreateReservationInteractorImpl implements CreateReservationInteractor {
    private Reservation reservation;
    private Callback callback;

    @Override
    public void initiate(Reservation reservation, Callback callback) {
        this.reservation = reservation;
        this.callback = callback;
        InteractorExecutor.getInstance().run(this);
    }

    @Override
    public void interact() {
        ReservationService reservationService = ApiClient.getInstance().getRetrofit().create(ReservationService.class);
        Call<Reservation> call = reservationService.createReservation(reservation);

        call.enqueue(new HandledCallback<Reservation>() {
            @Override
            public void onSuccess(Reservation response) {
                notifySuccess(response);
            }

            @Override
            public void onError(ExceptionInfo exceptionInfo) {
                notifyFailure(exceptionInfo);
            }
        });

    }


    private void notifySuccess(final Reservation reservation) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onCreateReservationsSuccess(reservation);
            }
        });
    }

    private void notifyFailure(final ExceptionInfo reason) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onCreateReservationsError(reason);
            }
        });
    }
}
