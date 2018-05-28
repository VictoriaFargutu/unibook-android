package com.fargutuvictoria.unibook.network.interactor.password;

import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.User;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

public interface ResetPasswordInteractor extends Interactor {
    interface Callback {

        void onResetPasswordSuccess(User user);

        void onResetPasswordError(ExceptionInfo exceptionInfo);
    }

    void initiate(String password, Callback callback);
}
