package com.fargutuvictoria.unibook.ui.filter;


import android.widget.Toast;

import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.StudentsGroup;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.network.interactor.filter.GetGroupsInteractor;
import com.fargutuvictoria.unibook.network.interactor.filter.GetGroupsInteractorImpl;

import java.util.List;

public class ReservationFilterPresenter implements ReservationFilterContract.Presenter, GetGroupsInteractor.Callback {
    private ReservationFilterContract.View view;

    public ReservationFilterPresenter(ReservationFilterContract.View view) {
        this.view = view;
    }

    @Override
    public void loadStudentsGroups() {
        GetGroupsInteractor getGroupsInteractor = new GetGroupsInteractorImpl();
        getGroupsInteractor.initiate(this);
    }

    @Override
    public void onGetGroupsSuccess(List<StudentsGroup> studentsGroups) {
        view.showStudentsGroups(studentsGroups);
    }

    @Override
    public void onGetGroupsError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }
}
