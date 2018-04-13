package com.fargutuvictoria.unibook.network.interactor.login;

import com.fargutuvictoria.commons.model.AuthSession;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

public interface LoginInteractor extends Interactor {

    interface Callback {

        void onLoginSuccess(AuthSession authSession);

        void onLoginError(ExceptionInfo exceptionInfo);
    }

    void initiate(String username, String password, Callback callback);
}
