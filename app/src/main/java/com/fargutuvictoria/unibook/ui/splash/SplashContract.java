package com.fargutuvictoria.unibook.ui.splash;

public interface SplashContract {
    interface View {
        void goToHomeActivity();

        void goToLoginActivity();
    }

    interface Presenter {
        boolean doSessionValidation();
    }
}
