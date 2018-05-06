package com.fargutuvictoria.unibook.ui.home;

import android.widget.Toast;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.network.interactor.home.LaboratoryRoomInteractor;
import com.fargutuvictoria.unibook.network.interactor.home.LaboratoryRoomInteractorImpl;
import com.fargutuvictoria.unibook.network.interactor.home.SeminarRoomInteractor;
import com.fargutuvictoria.unibook.network.interactor.home.SeminarRoomInteractorImpl;
import com.fargutuvictoria.unibook.ui.home.adapter.ActionHandler;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter, ActionHandler, LaboratoryRoomInteractor.Callback, SeminarRoomInteractor.Callback {
    private HomeContract.View mView;

    private LaboratoryRoomInteractor laboratoryRoomIntercator;
    private SeminarRoomInteractor seminarRoomInteractor;

    public HomePresenter(HomeContract.View startContractView) {
        this.mView = startContractView;
    }

    @Override
    public void onArrowClick(Classroom classroom) {
    }

    @Override
    public void loadClassrooms() {
        this.laboratoryRoomIntercator = new LaboratoryRoomInteractorImpl();
        this.seminarRoomInteractor = new SeminarRoomInteractorImpl();
//        laboratoryRoomIntercator.initiate(this);
//        seminarRoomInteractor.initiate(this);
    }

    @Override
    public void onLoadSeminarRoomsSuccess(List<Classroom> seminarRooms) {
        mView.showClassroomsLoaded(seminarRooms);
    }

    @Override
    public void onLoadLabRoomsSuccess(List<Classroom> labRooms) {
        mView.showClassroomsLoaded(labRooms);
    }

    @Override
    public void onLoadLabRoomsError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadSeminarRoomsError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }
}
