package com.fargutuvictoria.unibook.ui.login;

import android.widget.Toast;

import com.fargutuvictoria.commons.model.AuthSession;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.preferences.SharedPreferencesHandler;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.auth.UserSession;
import com.fargutuvictoria.unibook.network.interactor.login.LoginInteractor;

public class LoginPresenter implements LoginContract.Presenter, LoginInteractor.Callback {

    private LoginContract.View mView;
    private SharedPreferencesHandler mSPreferenceHandler;
    private LoginInteractor loginInteractor;

    public LoginPresenter(SharedPreferencesHandler sharedPreferencesHandler,
                          LoginContract.View startContractView,
                          LoginInteractor loginInteractor) {
        this.mView = startContractView;
        this.mSPreferenceHandler = sharedPreferencesHandler;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void doLogin(String username, String password) {
        loginInteractor.initiate(username, password, this);
    }

    @Override
    public void onLoginSuccess(AuthSession authSession) {
        mSPreferenceHandler.saveSessionToken(authSession.getSessionToken());
        UserSession.getInstance().setLoggedInUser(authSession.getUser());
        mView.goToNextActivity();
    }

    @Override
    public void onLoginError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }
}
