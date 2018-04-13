package com.fargutuvictoria.unibook.network.interactor.login;

import android.util.Base64;

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

    private MainThread mainThread;
    private InteractorExecutor interactorExecutor;
    private AuthService mAuthService;

    public LoginInteractorImpl(MainThread mainThread, InteractorExecutor interactorExecutor, AuthService mAuthService) {
        this.mainThread = mainThread;
        this.interactorExecutor = interactorExecutor;
        this.mAuthService = mAuthService;
    }

    @Override
    public void initiate(String username, String password, Callback callback) {
        this.username = username;
        this.password = password;
        this.callback = callback;
        interactorExecutor.run(this);
    }

    @Override
    public void interact() {
        String credentials = username + ":" + password;
        String base64 = Base64.encodeToString(credentials.getBytes(), Base64.DEFAULT);
        String auth = "Basic " + base64.replaceAll("\n", "");
        Call<AuthSession> call = mAuthService.login(auth);

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
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onLoginSuccess(session);
            }
        });
    }

    private void notifyFailure(final ExceptionInfo reason) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onLoginError(reason);
            }
        });
    }
}
