package com.fargutuvictoria.unibook.ui.home.classroom.lab;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.unibook.ui.home.adapter.classroom.ActionHandler;

import java.util.List;

public interface LabRoomContract {
    interface Fragment {
        void showLabRoomsLoaded(List<Classroom> classrooms);
        void openReservationActivity();
    }

    interface Presenter extends ActionHandler {
        void loadLabRooms();
    }
}
