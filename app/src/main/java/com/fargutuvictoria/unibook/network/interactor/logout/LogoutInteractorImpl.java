package com.fargutuvictoria.unibook.network.interactor.logout;

import com.fargutuvictoria.api.retrofit.callback.HandledCallback;
import com.fargutuvictoria.api.retrofit.service.AuthService;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.ApiClient;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;

import retrofit2.Call;

public class LogoutInteractorImpl implements LogoutInteractor {
    private Callback callback;

    @Override
    public void initiate(Callback callback) {
        this.callback = callback;
        InteractorExecutor.getInstance().run(this);
    }

    @Override
    public void interact() {

        AuthService authService = ApiClient.getInstance().getRetrofit().create(AuthService.class);
        Call<Void> call = authService.logout();

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
                callback.onLogoutSuccess();
            }
        });
    }

    private void notifyFailure(final ExceptionInfo reason) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onLogoutError(reason);
            }
        });
    }
}
