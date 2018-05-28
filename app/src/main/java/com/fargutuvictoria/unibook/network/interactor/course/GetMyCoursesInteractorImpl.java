package com.fargutuvictoria.unibook.network.interactor.course;

import com.fargutuvictoria.api.retrofit.callback.HandledCallback;
import com.fargutuvictoria.api.retrofit.service.CourseService;
import com.fargutuvictoria.commons.model.Course;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.ApiClient;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;
import com.fargutuvictoria.unibook.preferences.SharedPreferencesHandler;

import java.util.List;

import retrofit2.Call;

public class GetMyCoursesInteractorImpl implements GetMyCoursesInteractor {
    private Callback callback;

    @Override
    public void initiate(Callback callback) {
        this.callback = callback;
        InteractorExecutor.getInstance().run(this);
    }

    @Override
    public void interact() {

        CourseService courseService = ApiClient.getInstance().getRetrofit().create(CourseService.class);
        Call<List<Course>> call = courseService.getAllCourses();

        call.enqueue(new HandledCallback<List<Course>>() {
            @Override
            public void onSuccess(List<Course> response) {
                notifySuccess(response);
            }

            @Override
            public void onError(ExceptionInfo exceptionInfo) {
                notifyFailure(exceptionInfo);
            }
        });
    }

    private void notifySuccess(final List<Course> courses) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onGetMyCoursesSuccess(courses);
            }
        });
    }

    private void notifyFailure(final ExceptionInfo reason) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onGetMyCoursesError(reason);
            }
        });
    }
}
