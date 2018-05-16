package com.fargutuvictoria.unibook.ui.home.classroom.course;

import android.widget.Toast;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.network.interactor.home.CourseRoomInteractor;
import com.fargutuvictoria.unibook.network.interactor.home.CourseRoomInteractorImpl;

import java.util.List;

public class CourseRoomPresenter implements CourseRoomContract.Presenter, CourseRoomInteractor.Callback {
    private CourseRoomContract.Fragment fragment;

    public CourseRoomPresenter(CourseRoomContract.Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onArrowClick(Classroom classroom) {
        fragment.openReservationActivity(classroom);
    }

    @Override
    public void onLoadCourseRoomsSuccess(List<Classroom> courseRooms) {
        fragment.showCourseRoomsLoaded(courseRooms);
    }

    @Override
    public void loadCourseRooms() {
        CourseRoomInteractor courseRoomInteractor = new CourseRoomInteractorImpl();
        courseRoomInteractor.initiate(this);
    }

    @Override
    public void onLoadCourseRoomsError(ExceptionInfo exceptionInfo) {
        Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
    }
}
