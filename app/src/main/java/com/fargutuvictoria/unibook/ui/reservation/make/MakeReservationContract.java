package com.fargutuvictoria.unibook.ui.reservation.make;


import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.Filter;
import com.fargutuvictoria.commons.model.FreeOption;
import com.fargutuvictoria.unibook.ui.reservation.adapter.freeoptions.ActionHandler;

import java.util.List;

public interface MakeReservationContract {
    interface Fragment {
        void showOptionsLoaded(List<FreeOption> freeOptions);

        void openFreeOptionActivity(FreeOption freeOption);
        void openFilterActivity(FreeOption freeOption);
    }

    interface Presenter extends ActionHandler {
        void loadFreeOptionsByClassroom(Classroom classroom);

        void loadFreeOptionsByFilter(Filter filter);

        void loadFreeOptions();
    }
}
