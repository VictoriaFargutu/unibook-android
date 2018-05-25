package com.fargutuvictoria.unibook.network.interactor.free_option;

import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.Filter;
import com.fargutuvictoria.commons.model.FreeOption;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

import java.util.List;

public interface GetFreeOptionsByFilterInteractor extends Interactor {
    interface Callback {
        void onGetFreeOptionsSuccess(List<FreeOption> freeOptionList);

        void onGetFreeOptionsError(ExceptionInfo exceptionInfo);

    }

    void initiate(Filter filter, GetFreeOptionsByClassroomInteractor.Callback callback);
}
