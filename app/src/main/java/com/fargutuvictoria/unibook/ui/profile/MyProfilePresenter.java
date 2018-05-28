package com.fargutuvictoria.unibook.ui.profile;

public class MyProfilePresenter implements MyProfileContract.Presenter {
    private MyProfileContract.View view;

    public MyProfilePresenter(MyProfileContract.View view) {
        this.view = view;
    }

}
