package com.fargutuvictoria.unibook.ui.home;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.unibook.ui.home.adapter.ActionHandler;

import java.util.List;

public interface HomeContract {

    interface View {
        void showClassroomsLoaded(List<Classroom> classrooms);
    }

    interface Presenter extends ActionHandler {
        void loadClassrooms();
    }
}
