package com.fargutuvictoria.unibook.ui.free_option;

import com.fargutuvictoria.commons.model.Course;
import com.fargutuvictoria.commons.model.StudentsGroup;

import java.util.List;

public interface FreeOptionContract {
    interface View {
        void showCourses(List<Course> courses);

        void showStudentsGroups(List<StudentsGroup> studentsGroups);

        void showSubgroups();
    }

    interface Presenter {
        void loadCourses();
        void loadStudentsGroups();
    }
}
