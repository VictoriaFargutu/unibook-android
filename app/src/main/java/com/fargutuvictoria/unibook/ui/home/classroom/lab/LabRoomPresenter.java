package com.fargutuvictoria.unibook.ui.home.classroom.lab;

import android.widget.Toast;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.network.interactor.home.LaboratoryRoomInteractor;
import com.fargutuvictoria.unibook.network.interactor.home.LaboratoryRoomInteractorImpl;

import java.util.List;

public class LabRoomPresenter implements LabRoomContract.Presenter, LaboratoryRoomInteractor.Callback {
    private LabRoomContract.Fragment fragment;

    public LabRoomPresenter(LabRoomContract.Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onArrowClick(Classroom classroom) {
        fragment.openReservationActivity(classroom);
    }

    @Override
    public void onLoadLabRoomsSuccess(List<Classroom> labRooms) {
        fragment.showLabRoomsLoaded(labRooms);
    }

    @Override
    public void loadLabRooms() {
        LaboratoryRoomInteractor laboratoryRoomInteractor = new LaboratoryRoomInteractorImpl();
        laboratoryRoomInteractor.initiate(this);
    }

    @Override
    public void onLoadLabRoomsError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }
}
