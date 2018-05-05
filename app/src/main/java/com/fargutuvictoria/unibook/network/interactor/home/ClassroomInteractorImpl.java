package com.fargutuvictoria.unibook.network.interactor.home;

import com.fargutuvictoria.api.retrofit.callback.HandledCallback;
import com.fargutuvictoria.api.retrofit.service.ClassroomService;
import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.ApiClient;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;

import java.util.List;

import retrofit2.Call;

/**
 * Created by fargutuvictoria on 05/05/2018.
 */

public class ClassroomInteractorImpl implements ClassroomInteractor {
    private Callback callback;

    @Override
    public void initiate(Callback callback) {
        this.callback = callback;
        InteractorExecutor.getInstance().run(this);
    }

    @Override
    public void interact() {
        ClassroomService classroomService = ApiClient.getInstance().getRetrofit().create(ClassroomService.class);
        Call<List<Classroom>> call = classroomService.getAllCourseClassrooms();

        call.enqueue(new HandledCallback<List<Classroom>>() {
            @Override
            public void onSuccess(List<Classroom> response) {
                notifySuccess(response);
            }

            @Override
            public void onError(ExceptionInfo error) {
                notifyFailure(error);
            }
        });
    }

    private void notifySuccess(final List<Classroom> classrooms) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onLoadClassroomsSuccess(classrooms);
            }
        });
    }

    private void notifyFailure(final ExceptionInfo reason) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onLoadClassroomsEror(reason);
            }
        });
    }
}
