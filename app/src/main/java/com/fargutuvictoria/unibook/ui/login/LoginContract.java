package com.fargutuvictoria.unibook.ui.login;

public interface LoginContract {

    interface View {

        void goToNextActivity();
    }

    interface Presenter {

        void doLogin(String username, String password);
    }
}
