package com.fargutuvictoria.unibook.ui.home;

import com.fargutuvictoria.unibook.preferences.SharedPreferencesHandler;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;
    private SharedPreferencesHandler mSPreferenceHandler;


    public HomePresenter(SharedPreferencesHandler sharedPreferencesHandler,
                         HomeContract.View startContractView) {
        this.mSPreferenceHandler = sharedPreferencesHandler;
        this.mView = startContractView;
    }
}
