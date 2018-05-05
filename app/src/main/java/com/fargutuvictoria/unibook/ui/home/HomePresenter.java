package com.fargutuvictoria.unibook.ui.home;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.unibook.preferences.SharedPreferencesHandler;
import com.fargutuvictoria.unibook.ui.home.adapter.ActionHandler;

public class HomePresenter implements HomeContract.Presenter, ActionHandler {
    private HomeContract.View mView;
    private SharedPreferencesHandler mSPreferenceHandler;


    public HomePresenter(SharedPreferencesHandler sharedPreferencesHandler,
                         HomeContract.View startContractView) {
        this.mSPreferenceHandler = sharedPreferencesHandler;
        this.mView = startContractView;
    }

    @Override
    public void onArrowClick(Classroom classroom) {
    }

    @Override
    public void loadClassrooms() {

    }
}
