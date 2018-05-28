package com.fargutuvictoria.unibook.network.interactor.password;

import android.util.Base64;

import com.fargutuvictoria.api.retrofit.callback.HandledCallback;
import com.fargutuvictoria.api.retrofit.service.AuthService;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.User;
import com.fargutuvictoria.unibook.ApiClient;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;

import retrofit2.Call;

public class ResetPasswordInteractorImpl implements ResetPasswordInteractor {
    private String password;
    private Callback callback;

    @Override
    public void initiate(String password, Callback callback) {
        this.password = password;
        this.callback = callback;
        InteractorExecutor.getInstance().run(this);
    }

    @Override
    public void interact() {
        AuthService authService = ApiClient.getInstance().getRetrofit().create(AuthService.class);
        String base64 = Base64.encodeToString(password.getBytes(), Base64.DEFAULT);
        Call<User> call = authService.resetPassword(base64);

        call.enqueue(new HandledCallback<User>() {
            @Override
            public void onSuccess(User response) {
                notifySuccess(response);
            }

            @Override
            public void onError(ExceptionInfo error) {
                notifyFailure(error);
            }
        });
    }

    private void notifySuccess(final User user) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onResetPasswordSuccess(user);
            }
        });
    }

    private void notifyFailure(final ExceptionInfo reason) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onResetPasswordError(reason);
            }
        });
    }
}

