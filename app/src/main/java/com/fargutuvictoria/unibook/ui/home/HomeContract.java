package com.fargutuvictoria.unibook.ui.home;

import com.fargutuvictoria.unibook.ui.home.adapter.classroom.ActionHandler;

public interface HomeContract {

    interface View {
        void openReservationActivity();

        void openLoginActivity();
    }

    interface Presenter extends ActionHandler {
        void logout();
    }
}
