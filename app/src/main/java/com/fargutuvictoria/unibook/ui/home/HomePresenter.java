package com.fargutuvictoria.unibook.ui.home;

import android.widget.Toast;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.network.interactor.logout.LogoutInteractor;
import com.fargutuvictoria.unibook.network.interactor.logout.LogoutInteractorImpl;
import com.fargutuvictoria.unibook.ui.home.adapter.classroom.ActionHandler;

public class HomePresenter implements HomeContract.Presenter, ActionHandler, LogoutInteractor.Callback {
    private HomeContract.View mView;

    public HomePresenter(HomeContract.View startContractView) {
        this.mView = startContractView;
    }

    @Override
    public void onArrowClick(Classroom classroom) {
        mView.openReservationActivity();
    }

    @Override
    public void logout() {
        LogoutInteractor logoutInteractor = new LogoutInteractorImpl();
        logoutInteractor.initiate(this);
    }

    @Override
    public void onLogoutSuccess() {
        mView.openLoginActivity();
    }

    @Override
    public void onLogoutError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }
}
