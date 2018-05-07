package com.fargutuvictoria.unibook.network.interactor.session.validation;

import com.fargutuvictoria.commons.model.AuthSession;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.network.interactor.Interactor;


public interface SessionValidationInteractor extends Interactor {
    interface Callback {
        void onSessionValidationSuccess(AuthSession authSession);

        void onSessionValidationError(ExceptionInfo exceptionInfo);

    }

    void initiate(Callback callback);
}
