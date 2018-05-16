package com.fargutuvictoria.unibook.network.interactor.free_option;


import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.FreeOption;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

import java.util.List;

public interface GetFreeOptionsByClassroomInteractor extends Interactor {
    interface Callback {

        void onGetFreeOptionsSuccess(List<FreeOption> freeOptionList);

        void onGetFreeOptionsError(ExceptionInfo exceptionInfo);
    }

    void initiate(Classroom classroom, Callback callback);
}
