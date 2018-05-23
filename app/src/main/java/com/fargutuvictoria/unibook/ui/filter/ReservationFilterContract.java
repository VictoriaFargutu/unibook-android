package com.fargutuvictoria.unibook.ui.filter;


public interface ReservationFilterContract {
    interface View {
        void showClassroomTypes();

        void showWeekTypes();

        void showHours();

        void showYear();

        void showSpecializations();

        void showStudentsGroups();

        void showSubgroups();
    }

    interface Presenter {
    }
}
