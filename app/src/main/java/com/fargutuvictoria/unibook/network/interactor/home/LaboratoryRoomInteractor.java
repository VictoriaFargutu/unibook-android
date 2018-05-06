package com.fargutuvictoria.unibook.network.interactor.home;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

import java.util.List;

public interface LaboratoryRoomInteractor extends Interactor {
    interface Callback {
        void onLoadLabRoomsSuccess(List<Classroom> labRooms);

        void onLoadLabRoomsError(ExceptionInfo exceptionInfo);
    }

    void initiate(Callback callback);
}
