package com.fargutuvictoria.unibook.network.interactor.login;

import android.util.Base64;

import com.fargutuvictoria.unibook.ApiClient;
import com.fargutuvictoria.api.retrofit.callback.HandledCallback;
import com.fargutuvictoria.api.retrofit.service.AuthService;
import com.fargutuvictoria.commons.model.AuthSession;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;

import retrofit2.Call;

public class LoginInteractorImpl implements LoginInteractor {

    private String username;
    private String password;
    private Callback callback;

    @Override
    public void initiate(String username, String password, Callback callback) {
        this.username = username;
        this.password = password;
        this.callback = callback;
        InteractorExecutor.getInstance().run(this);
    }

    @Override
    public void interact() {
        AuthService authService = ApiClient.getInstance().getRetrofit().create(AuthService.class);
        String credentials = username + ":" + password;
        String base64 = Base64.encodeToString(credentials.getBytes(), Base64.DEFAULT);
        String auth = "Basic " + base64.replaceAll("\n", "");
        Call<AuthSession> call = authService.login(auth);

        call.enqueue(new HandledCallback<AuthSession>() {
            @Override
            public void onSuccess(AuthSession response) {
                notifySuccess(response);
            }

            @Override
            public void onError(ExceptionInfo error) {
                notifyFailure(error);
            }
        });
    }

    private void notifySuccess(final AuthSession session) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onLoginSuccess(session);
            }
        });
    }

    private void notifyFailure(final ExceptionInfo reason) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onLoginError(reason);
            }
        });
    }
}
