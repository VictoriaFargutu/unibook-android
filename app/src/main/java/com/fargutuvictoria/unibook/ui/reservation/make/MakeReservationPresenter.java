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

import java.util.List;

public class MakeReservationPresenter implements MakeReservationContract.Presenter, GetFreeOptionsByClassroomInteractor.Callback {
    private MakeReservationContract.Fragment fragment;

    public MakeReservationPresenter(MakeReservationContract.Fragment fragment) {
        this.fragment = fragment;
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
    public void onGetFreeOptionsSuccess(List<FreeOption> freeOptionList) {
        fragment.showOptionsLoaded(freeOptionList);
    }

    @Override
    public void onGetFreeOptionsError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }
}
