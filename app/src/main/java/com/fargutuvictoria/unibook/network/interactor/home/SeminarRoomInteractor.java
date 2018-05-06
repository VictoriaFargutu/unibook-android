package com.fargutuvictoria.unibook.network.interactor.home;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

import java.util.List;

public interface SeminarRoomInteractor extends Interactor {
    interface Callback {
        void onLoadSeminarRoomsSuccess(List<Classroom> seminarRooms);

        void onLoadSeminarRoomsError(ExceptionInfo exceptionInfo);
    }

    void initiate(Callback callback);
}
