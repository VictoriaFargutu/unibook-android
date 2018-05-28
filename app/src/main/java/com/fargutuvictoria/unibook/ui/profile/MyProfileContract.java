package com.fargutuvictoria.unibook.ui.profile;

public interface MyProfileContract {
    interface View {
        void passwordChanged();
    }

    interface Presenter {
        void resetPassword(String password);
    }
}
