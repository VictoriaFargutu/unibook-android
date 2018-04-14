package com.fargutuvictoria.unibook.network.interactor.session.validation;

import com.fargutuvictoria.api.retrofit.ApiClient;
import com.fargutuvictoria.api.retrofit.callback.HandledCallback;
import com.fargutuvictoria.api.retrofit.service.AuthService;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.User;
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
        Call<User> call = authService.validateToken();

        call.enqueue(new HandledCallback<User>() {
            @Override
            public void onSuccess(User response) {
                notifySuccess(response);
            }

            @Override
            public void onError(ExceptionInfo exceptionInfo) {
                notifyFailure(exceptionInfo);
            }
        });
    }

    private void notifySuccess(final User user) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onSessionValidationSuccess(user);
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
