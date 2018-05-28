package com.fargutuvictoria.unibook.network.interactor.course;

import com.fargutuvictoria.commons.model.Course;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

import java.util.List;

public interface GetMyCoursesInteractor extends Interactor {
    interface Callback {

        void onGetMyCoursesSuccess(List<Course> courses);

        void onGetMyCoursesError(ExceptionInfo exceptionInfo);
    }

    void initiate(Callback callback);
}
