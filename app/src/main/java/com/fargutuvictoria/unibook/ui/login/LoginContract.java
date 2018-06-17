package com.fargutuvictoria.unibook.ui.login;

import com.fargutuvictoria.commons.model.ExceptionInfo;

public interface LoginContract {

    interface View {

        void goToNextActivity();

        void displayError(ExceptionInfo exceptionInfo);
    }

    interface Presenter {

        void doLogin(String username, String password);
    }
}
