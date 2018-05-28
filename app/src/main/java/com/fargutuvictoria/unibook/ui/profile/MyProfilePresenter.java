package com.fargutuvictoria.unibook.ui.profile;

import android.widget.Toast;

import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.User;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.network.interactor.password.ResetPasswordInteractor;
import com.fargutuvictoria.unibook.network.interactor.password.ResetPasswordInteractorImpl;

public class MyProfilePresenter implements MyProfileContract.Presenter, ResetPasswordInteractor.Callback {
    private MyProfileContract.View view;

    public MyProfilePresenter(MyProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void resetPassword(String password) {
        ResetPasswordInteractor resetPasswordInteractor = new ResetPasswordInteractorImpl();
        resetPasswordInteractor.initiate(password, this);
    }

    @Override
    public void onResetPasswordSuccess(User user) {
        view.passwordChanged();
    }

    @Override
    public void onResetPasswordError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }
}
