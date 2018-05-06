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

public class SeminarRoomInteractorImpl implements SeminarRoomInteractor {
    private Callback callback;

    @Override
    public void initiate(Callback callback) {
        this.callback = callback;
        InteractorExecutor.getInstance().run(this);
    }

    @Override
    public void interact() {
        ClassroomService classroomService = ApiClient.getInstance().getRetrofit().create(ClassroomService.class);
        Call<List<Classroom>> call = classroomService.getAllSeminarClassrooms();

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
                callback.onLoadSeminarRoomsSuccess(classrooms);
            }
        });
    }

    private void notifyFailure(final ExceptionInfo reason) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onLoadSeminarRoomsError(reason);
            }
        });
    }
}
