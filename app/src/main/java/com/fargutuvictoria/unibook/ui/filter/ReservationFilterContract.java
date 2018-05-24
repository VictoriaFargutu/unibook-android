package com.fargutuvictoria.unibook.ui.filter;


import com.fargutuvictoria.commons.model.StudentsGroup;

import java.util.List;

public interface ReservationFilterContract {
    interface View {
        void showClassroomTypes();

        void showDays();

        void showWeekTypes();

        void showHours();

        void showYear();

        void showSpecializations();

        void showStudentsGroups(List<StudentsGroup> studentsGroups);

        void showSubgroups();
    }

    interface Presenter {
        void loadStudentsGroups();
    }
}
