package com.fargutuvictoria.unibook.network.interactor.session.validation;

import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.User;
import com.fargutuvictoria.unibook.network.interactor.Interactor;


public interface SessionValidationInteractor extends Interactor {
    interface Callback {
        void onSessionValidationSuccess(User user);

        void onSessionValidationError(ExceptionInfo exceptionInfo);

    }

    void initiate(Callback callback);
}
