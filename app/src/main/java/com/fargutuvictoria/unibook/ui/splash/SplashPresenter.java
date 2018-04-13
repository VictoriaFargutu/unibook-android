package com.fargutuvictoria.unibook.ui.splash;

import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.User;
import com.fargutuvictoria.commons.preferences.SharedPreferencesHandler;
import com.fargutuvictoria.unibook.auth.UserSession;
import com.fargutuvictoria.unibook.network.interactor.session.validation.SessionValidationInteractor;

/**
 * Created by fargutuvictoria on 23/02/2018.
 */

public class SplashPresenter implements SplashContract.Presenter, SessionValidationInteractor.Callback {

    private SplashContract.View mView;
    private SharedPreferencesHandler mSPreferenceHandler;
    private SessionValidationInteractor sessionValidationInteractor;


    public SplashPresenter(SharedPreferencesHandler sharedPreferencesHandler, SplashContract.View startContractView, SessionValidationInteractor sessionValidationInteractor) {
        this.mView = startContractView;
        this.mSPreferenceHandler = sharedPreferencesHandler;
        this.sessionValidationInteractor = sessionValidationInteractor;
    }

    @Override
    public boolean doSessionValidation() {
        if (mSPreferenceHandler.getSessionToken() != null) {
            sessionValidationInteractor.initiate(this);
            return true;
        }
        return false;
    }

    @Override
    public void onSessionValidationSuccess(User user) {
        UserSession.getInstance().setLoggedInUser(user);
        mView.goToHomeActivity();
    }

    @Override
    public void onSessionValidationError(ExceptionInfo exceptionInfo) {
        mView.goToLoginActivity();
    }
}
