package com.fargutuvictoria.unibook.network.interactor.filter;


import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.StudentsGroup;
import com.fargutuvictoria.unibook.network.interactor.Interactor;

import java.util.List;

public interface GetGroupsInteractor extends Interactor {
    interface Callback {

        void onGetGroupsSuccess(List<StudentsGroup> studentsGroups);

        void onGetGroupsError(ExceptionInfo exceptionInfo);
    }

    void initiate(Callback callback);
}
