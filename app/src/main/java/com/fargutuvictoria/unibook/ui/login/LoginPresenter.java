package com.fargutuvictoria.unibook.ui.login;

import android.widget.Toast;

import com.fargutuvictoria.commons.model.AuthSession;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.auth.UserSession;
import com.fargutuvictoria.unibook.network.interactor.login.LoginInteractor;
import com.fargutuvictoria.unibook.network.interactor.login.LoginInteractorImpl;

public class LoginPresenter implements LoginContract.Presenter, LoginInteractor.Callback {

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View startContractView) {
        this.mView = startContractView;
    }

    @Override
    public void doLogin(String username, String password) {
        new LoginInteractorImpl().initiate(username, password, this);
    }

    @Override
    public void onLoginSuccess(AuthSession authSession) {
       // SharedPreferencesHandler..saveSessionToken(authSession.getSessionToken());
        UserSession.getInstance().setLoggedInUser(authSession.getUser());
        mView.goToNextActivity();
    }

    @Override
    public void onLoginError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }
}
