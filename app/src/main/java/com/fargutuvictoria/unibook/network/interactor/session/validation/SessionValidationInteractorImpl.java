package com.fargutuvictoria.unibook.network.interactor.session.validation;

import com.fargutuvictoria.api.retrofit.callback.HandledCallback;
import com.fargutuvictoria.api.retrofit.service.AuthService;
import com.fargutuvictoria.commons.model.AuthSession;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.User;
import com.fargutuvictoria.unibook.ApiClient;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;

import retrofit2.Call;

public class SessionValidationInteractorImpl implements SessionValidationInteractor {
    private SessionValidationInteractor.Callback callback;

    @Override
    public void initiate(Callback callback) {
        this.callback = callback;
        InteractorExecutor.getInstance().run(this);
    }

    @Override
    public void interact() {
        AuthService authService = ApiClient.getInstance().getRetrofit().create(AuthService.class);
        Call<AuthSession> call = authService.validateToken();

        call.enqueue(new HandledCallback<AuthSession>() {
            @Override
            public void onSuccess(AuthSession response) {
                notifySuccess(response);
            }

            @Override
            public void onError(ExceptionInfo exceptionInfo) {
                notifyFailure(exceptionInfo);
            }
        });
    }

    private void notifySuccess(final AuthSession authSession) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onSessionValidationSuccess(authSession);
            }
        });
    }

    private void notifyFailure(final ExceptionInfo reason) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onSessionValidationError(reason);
            }
        });
    }
}
