package com.fargutuvictoria.unibook.ui.home.classroom.course;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.unibook.ui.home.adapter.ActionHandler;

import java.util.List;

/**
 * Created by fargutuvictoria on 06/05/2018.
 */

public interface CourseRoomContract {
    interface Fragment {
        void showCourseRoomsLoaded(List<Classroom> classrooms);
    }

    interface Presenter extends ActionHandler {
        void loadCourseRooms();
    }
}
