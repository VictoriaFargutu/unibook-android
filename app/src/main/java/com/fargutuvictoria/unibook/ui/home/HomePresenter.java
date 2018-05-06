package com.fargutuvictoria.unibook.ui.home;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.unibook.ui.home.adapter.classroom.ActionHandler;

public class HomePresenter implements HomeContract.Presenter, ActionHandler {
    private HomeContract.View mView;

    public HomePresenter(HomeContract.View startContractView) {
        this.mView = startContractView;
    }

    @Override
    public void onArrowClick(Classroom classroom) {
        mView.openReservationActivity();
    }
}
