package com.fargutuvictoria.unibook.ui.free_option;

import android.widget.Toast;

import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.StudentsGroup;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.network.interactor.filter.GetGroupsInteractor;
import com.fargutuvictoria.unibook.network.interactor.filter.GetGroupsInteractorImpl;

import java.util.List;

public class FreeOptionPresenter implements FreeOptionContract.Presenter, GetGroupsInteractor.Callback {
    private FreeOptionContract.View view;

    public FreeOptionPresenter(FreeOptionContract.View view) {
        this.view = view;
    }

    @Override
    public void loadCourses() {

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
