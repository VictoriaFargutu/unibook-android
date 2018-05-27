package com.fargutuvictoria.unibook.network.interactor.course;

import com.fargutuvictoria.commons.model.Course;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

import java.util.List;

public interface GetCoursesInteractor extends Interactor {
    interface Callback {

        void onGetCoursesSuccess(List<Course> courses);

        void onGetCoursesError(ExceptionInfo exceptionInfo);
    }

    void initiate(Callback callback);
}
