package com.fargutuvictoria.unibook.ui.reservation.make;


import android.widget.Toast;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.Filter;
import com.fargutuvictoria.commons.model.FreeOption;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.network.interactor.free_option.GetFreeOptionsByClassroomInteractor;
import com.fargutuvictoria.unibook.network.interactor.free_option.GetFreeOptionsByClassroomInteractorImpl;
import com.fargutuvictoria.unibook.network.interactor.free_option.GetFreeOptionsByFilterInteractor;
import com.fargutuvictoria.unibook.network.interactor.free_option.GetFreeOptionsByFilterInteractorImpl;
import com.fargutuvictoria.unibook.network.interactor.free_option.GetFreeOptionsInteractor;
import com.fargutuvictoria.unibook.network.interactor.free_option.GetFreeOptionsInteractorImpl;

import java.util.List;

public class MakeReservationPresenter implements MakeReservationContract.Presenter, GetFreeOptionsByClassroomInteractor.Callback, GetFreeOptionsByFilterInteractor.Callback, GetFreeOptionsInteractor.Callback {
    private MakeReservationContract.Fragment fragment;

    public MakeReservationPresenter(MakeReservationContract.Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onArrowClick(FreeOption freeOption) {
        if (freeOption.getDate() != null) {
            fragment.openFreeOptionActivity(freeOption);
        } else {
            fragment.openFilterActivity(freeOption);
            Toast.makeText(UnibookApplication.getInstance(), "You must select the date!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void loadFreeOptionsByClassroom(Classroom classroom) {
        GetFreeOptionsByClassroomInteractor getFreeOptionsByClassroomInteractor = new GetFreeOptionsByClassroomInteractorImpl();
        getFreeOptionsByClassroomInteractor.initiate(classroom, this);
    }

    @Override
    public void loadFreeOptionsByFilter(Filter filter) {
        GetFreeOptionsByFilterInteractor getFreeOptionsByFilterInteractor = new GetFreeOptionsByFilterInteractorImpl();
        getFreeOptionsByFilterInteractor.initiate(filter, this);
    }

    @Override
    public void loadFreeOptions() {
        GetFreeOptionsInteractor getFreeOptionsInteractor = new GetFreeOptionsInteractorImpl();
        getFreeOptionsInteractor.initiate(this);

    }

    @Override
    public void onGetFreeOptionsSuccess(List<FreeOption> freeOptionList) {
        fragment.showOptionsLoaded(freeOptionList);
    }

    @Override
    public void onGetFreeOptionsError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }
}
