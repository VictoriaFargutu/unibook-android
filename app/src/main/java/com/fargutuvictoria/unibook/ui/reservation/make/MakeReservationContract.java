package com.fargutuvictoria.unibook.ui.reservation.make;


import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.FreeOption;

import java.util.List;

public interface MakeReservationContract {
    interface Fragment {
        void showOptionsLoaded(List<FreeOption> freeOptions);
    }

    interface Presenter {
        void loadFreeOptions(Classroom classroom);
    }
}
