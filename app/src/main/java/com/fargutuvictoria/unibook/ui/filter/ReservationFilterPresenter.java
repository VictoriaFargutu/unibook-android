package com.fargutuvictoria.unibook.ui.filter;


import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.StudentsGroup;
import com.fargutuvictoria.unibook.network.interactor.filter.GetGroupsInteractor;

import java.util.List;

public class ReservationFilterPresenter implements GetGroupsInteractor.Callback {
    private GetGroupsInteractor getGroupsInteractor;

    public ReservationFilterPresenter(GetGroupsInteractor getGroupsInteractor) {
        this.getGroupsInteractor = getGroupsInteractor;
    }

    @Override
    public void onGetGroupsSuccess(List<StudentsGroup> studentsGroups) {

    }

    @Override
    public void onGetGroupsError(ExceptionInfo exceptionInfo) {

    }
}
