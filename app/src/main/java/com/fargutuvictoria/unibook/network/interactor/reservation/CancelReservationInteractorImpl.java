package com.fargutuvictoria.unibook.network.interactor.reservation;


import com.fargutuvictoria.api.retrofit.callback.HandledCallback;
import com.fargutuvictoria.api.retrofit.service.ReservationService;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.ApiClient;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;

import retrofit2.Call;

public class CancelReservationInteractorImpl implements CancelReservationInteractor {
    private Long reservationId;
    private Callback callback;

    @Override
    public void interact() {
        ReservationService reservationService = ApiClient.getInstance().getRetrofit().create(ReservationService.class);
        Call<Void> call = reservationService.cancelReservation(reservationId);

        call.enqueue(new HandledCallback<Void>() {
            @Override
            public void onSuccess(Void response) {
                notifySuccess();
            }

            @Override
            public void onError(ExceptionInfo exceptionInfo) {
                notifyFailure(exceptionInfo);
            }
        });

    }


    private void notifySuccess() {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onCancelReservationsSuccess();
            }
        });
    }

    private void notifyFailure(final ExceptionInfo reason) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onCancelReservationsError(reason);
            }
        });
    }


    @Override
    public void initiate(Long reservationId, Callback callback) {
        this.reservationId = reservationId;
        this.callback = callback;
        InteractorExecutor.getInstance().run(this);
    }
}
