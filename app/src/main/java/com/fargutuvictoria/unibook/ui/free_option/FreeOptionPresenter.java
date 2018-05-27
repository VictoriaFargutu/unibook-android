package com.fargutuvictoria.unibook.ui.free_option;

import android.widget.Toast;

import com.fargutuvictoria.commons.model.Course;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.Reservation;
import com.fargutuvictoria.commons.model.StudentsGroup;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.network.interactor.course.GetCoursesInteractor;
import com.fargutuvictoria.unibook.network.interactor.course.GetCoursesInteractorImpl;
import com.fargutuvictoria.unibook.network.interactor.filter.GetGroupsInteractor;
import com.fargutuvictoria.unibook.network.interactor.filter.GetGroupsInteractorImpl;
import com.fargutuvictoria.unibook.network.interactor.reservation.CreateReservationInteractor;
import com.fargutuvictoria.unibook.network.interactor.reservation.CreateReservationInteractorImpl;

import java.util.List;

public class FreeOptionPresenter implements FreeOptionContract.Presenter, GetGroupsInteractor.Callback, GetCoursesInteractor.Callback, CreateReservationInteractor.Callback {
    private FreeOptionContract.View view;

    public FreeOptionPresenter(FreeOptionContract.View view) {
        this.view = view;
    }

    @Override
    public void loadCourses() {
        GetCoursesInteractor getCoursesInteractor = new GetCoursesInteractorImpl();
        getCoursesInteractor.initiate(this);
    }

    @Override
    public void onGetCoursesSuccess(List<Course> courses) {
        view.showCourses(courses);
    }

    @Override
    public void onGetCoursesError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadStudentsGroups() {
        GetGroupsInteractor getGroupsInteractor = new GetGroupsInteractorImpl();
        getGroupsInteractor.initiate(this);
    }

    @Override
    public void onGetGroupsSuccess(List<StudentsGroup> studentsGroups) {
        view.showStudentsGroups(studentsGroups);
    }

    @Override
    public void onGetGroupsError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void makeReservation(Reservation reservation) {
        CreateReservationInteractor createReservationInteractor = new CreateReservationInteractorImpl();
        createReservationInteractor.initiate(reservation, this);
    }

    @Override
    public void onCreateReservationsSuccess(Reservation reservation) {
        Toast.makeText(UnibookApplication.getInstance(), "Reservation created!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateReservationsError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }
}
