package com.fargutuvictoria.unibook.network.interactor.logout;

import com.fargutuvictoria.commons.model.AuthSession;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

public interface LogoutInteractor extends Interactor {
    interface Callback {

        void onLogoutSuccess();

        void onLogoutError(ExceptionInfo exceptionInfo);
    }

    void initiate(Callback callback);
}
