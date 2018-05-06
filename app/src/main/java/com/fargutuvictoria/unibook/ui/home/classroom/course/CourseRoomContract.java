package com.fargutuvictoria.unibook.ui.home.classroom.course;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.unibook.ui.home.adapter.classroom.ActionHandler;

import java.util.List;

public interface CourseRoomContract {
    interface Fragment {
        void showCourseRoomsLoaded(List<Classroom> classrooms);

        void openReservationActivity();
    }

    interface Presenter extends ActionHandler {
        void loadCourseRooms();
    }
}
