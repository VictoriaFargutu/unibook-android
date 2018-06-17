package com.fargutuvictoria.unibook.ui.profile;

import com.fargutuvictoria.commons.model.ExceptionInfo;

public interface MyProfileContract {
    interface View {
        void passwordChanged();

        void passwordChangeError(ExceptionInfo exceptionInfo);
    }

    interface Presenter {
        void resetPassword(String password);
    }
}
