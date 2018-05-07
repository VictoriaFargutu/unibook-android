package com.fargutuvictoria.unibook.ui.splash;

import com.fargutuvictoria.commons.model.AuthSession;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.auth.UserSession;
import com.fargutuvictoria.unibook.network.interactor.session.validation.SessionValidationInteractor;
import com.fargutuvictoria.unibook.network.interactor.session.validation.SessionValidationInteractorImpl;
import com.fargutuvictoria.unibook.preferences.SharedPreferencesHandler;

/**
 * Created by fargutuvictoria on 23/02/2018.
 */

public class SplashPresenter implements SplashContract.Presenter, SessionValidationInteractor.Callback {

    private SplashContract.View mView;


    public SplashPresenter(SplashContract.View startContractView) {
        this.mView = startContractView;

    }

    @Override
    public boolean doSessionValidation() {
        if (SharedPreferencesHandler.getInstance().getSessionToken() != null) {
            new SessionValidationInteractorImpl().initiate(this);
            return true;
        }
        return false;
    }

    @Override
    public void onSessionValidationSuccess(AuthSession authSession) {
        UserSession.getInstance().setLoggedInUser(authSession.getUser());
        mView.goToHomeActivity();
    }

    @Override
    public void onSessionValidationError(ExceptionInfo exceptionInfo) {
        mView.goToLoginActivity();
    }
}
