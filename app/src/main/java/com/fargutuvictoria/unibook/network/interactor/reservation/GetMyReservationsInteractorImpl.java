package com.fargutuvictoria.unibook.network.interactor.reservation;


import com.fargutuvictoria.api.retrofit.callback.HandledCallback;
import com.fargutuvictoria.api.retrofit.service.ReservationService;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.Reservation;
import com.fargutuvictoria.unibook.ApiClient;
import com.fargutuvictoria.unibook.auth.UserSession;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;

import java.util.List;

import retrofit2.Call;

public class GetMyReservationsInteractorImpl implements GetMyReservationsInteractor {
    private Callback callback;

    @Override
    public void interact() {
        ReservationService reservationService = ApiClient.getInstance().getRetrofit().create(ReservationService.class);
        Call<List<Reservation>> call = reservationService.getReservationsByUserId(UserSession.getInstance().getLoggedInUser().getId());

        call.enqueue(new HandledCallback<List<Reservation>>() {
            @Override
            public void onSuccess(List<Reservation> response) {
                notifySuccess(response);
            }

            @Override
            public void onError(ExceptionInfo error) {
                notifyFailure(error);
            }
        });
    }

    private void notifySuccess(final List<Reservation> classrooms) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onLoadReservationsSuccess(classrooms);
            }
        });
    }

    private void notifyFailure(final ExceptionInfo reason) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onLoadReservationsError(reason);
            }
        });
    }

    @Override
    public void initiate(Callback callback) {
        this.callback = callback;
        InteractorExecutor.getInstance().run(this);
    }
}
