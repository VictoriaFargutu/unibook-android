package com.fargutuvictoria.unibook.network.interactor.home;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

import java.util.List;

public interface CourseRoomInteractor extends Interactor {
    interface Callback {
        void onLoadCourseRoomsSuccess(List<Classroom> courseRooms);

        void onLoadCourseRoomsError(ExceptionInfo exceptionInfo);
    }

    void initiate(Callback callback);
}
