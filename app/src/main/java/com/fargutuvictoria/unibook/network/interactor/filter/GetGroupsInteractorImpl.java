package com.fargutuvictoria.unibook.network.interactor.filter;

import com.fargutuvictoria.api.retrofit.callback.HandledCallback;
import com.fargutuvictoria.api.retrofit.service.StudentsGroupService;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.StudentsGroup;
import com.fargutuvictoria.unibook.ApiClient;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;

import java.util.List;

import retrofit2.Call;

public class GetGroupsInteractorImpl implements GetGroupsInteractor {
    private Callback callback;

    @Override
    public void interact() {
        StudentsGroupService studentsGroupService = ApiClient.getInstance().getRetrofit().create(StudentsGroupService.class);
        Call<List<StudentsGroup>> call = studentsGroupService.getAllStudentsGroups();

        call.enqueue(new HandledCallback<List<StudentsGroup>>() {
            @Override
            public void onSuccess(List<StudentsGroup> response) {
                notifySuccess(response);
            }

            @Override
            public void onError(ExceptionInfo exceptionInfo) {
                notifyFailure(exceptionInfo);
            }
        });
    }

    @Override
    public void initiate(Callback callback) {
        this.callback = callback;
        InteractorExecutor.getInstance().run(this);
    }

    private void notifySuccess(final List<StudentsGroup> studentsGroups) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onGetGroupsSuccess(studentsGroups);
            }
        });
    }

    private void notifyFailure(final ExceptionInfo reason) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onGetGroupsError(reason);
            }
        });
    }
}
