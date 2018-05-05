package com.fargutuvictoria.unibook.network.interactor.home;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

import java.util.List;

/**
 * Created by fargutuvictoria on 05/05/2018.
 */

public interface ClassroomInteractor extends Interactor {
    interface Callback {
        void onLoadClassroomsSuccess(List<Classroom> classrooms);

        void onLoadClassroomsEror(ExceptionInfo exceptionInfo);
    }

    void initiate(Callback callback);
}
