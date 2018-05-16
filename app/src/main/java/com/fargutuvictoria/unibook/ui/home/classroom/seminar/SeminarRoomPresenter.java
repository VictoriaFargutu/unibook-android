package com.fargutuvictoria.unibook.ui.home.classroom.seminar;


import android.widget.Toast;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.network.interactor.home.SeminarRoomInteractor;
import com.fargutuvictoria.unibook.network.interactor.home.SeminarRoomInteractorImpl;

import java.util.List;

public class SeminarRoomPresenter implements SeminarRoomContract.Presenter, SeminarRoomInteractor.Callback {
    private SeminarRoomContract.Fragment fragment;

    public SeminarRoomPresenter(SeminarRoomContract.Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onArrowClick(Classroom classroom) {
        fragment.openReservationActivity(classroom);
    }

    @Override
    public void onLoadSeminarRoomsSuccess(List<Classroom> seminarRooms) {
        fragment.showSeminarRoomsLoaded(seminarRooms);
    }

    @Override
    public void loadSeminarRooms() {
        SeminarRoomInteractor seminarRoomInteractor = new SeminarRoomInteractorImpl();
        seminarRoomInteractor.initiate(this);
    }

    @Override
    public void onLoadSeminarRoomsError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }
}
