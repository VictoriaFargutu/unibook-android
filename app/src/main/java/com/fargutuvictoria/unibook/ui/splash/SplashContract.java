package com.fargutuvictoria.unibook.ui.splash;

/**
 * Created by fargutuvictoria on 23/02/2018.
 */

public interface SplashContract {
    interface View {
        void goToHomeActivity();

        void goToLoginActivity();
    }

    interface Presenter {
        boolean doSessionValidation();
    }
}
