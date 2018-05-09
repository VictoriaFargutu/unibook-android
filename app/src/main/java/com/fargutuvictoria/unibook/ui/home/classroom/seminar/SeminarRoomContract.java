package com.fargutuvictoria.unibook.ui.home.classroom.seminar;


import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.unibook.ui.home.adapter.classroom.ActionHandler;

import java.util.List;

public interface SeminarRoomContract {
    interface Fragment {
        void showSeminarRoomsLoaded(List<Classroom> classrooms);
        void openReservationActivity();
    }

    interface Presenter extends ActionHandler {
        void loadSeminarRooms();
    }
}
