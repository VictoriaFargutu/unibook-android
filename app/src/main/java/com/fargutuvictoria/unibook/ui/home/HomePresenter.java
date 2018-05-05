package com.fargutuvictoria.unibook.ui.home;

import android.widget.Toast;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.network.interactor.home.ClassroomInteractor;
import com.fargutuvictoria.unibook.network.interactor.home.ClassroomInteractorImpl;
import com.fargutuvictoria.unibook.preferences.SharedPreferencesHandler;
import com.fargutuvictoria.unibook.ui.home.adapter.ActionHandler;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter, ActionHandler, ClassroomInteractor.Callback {
    private HomeContract.View mView;
    private SharedPreferencesHandler mSPreferenceHandler;

    private ClassroomInteractor classroomInteractor;

    public HomePresenter(HomeContract.View startContractView) {
//        ClassroomInteractor classroomInteractor){
//        this.mSPreferenceHandler = sharedPreferencesHandler;
            this.mView = startContractView;
//        this.classroomInteractor = classroomInteractor;
        }

        @Override
        public void onArrowClick (Classroom classroom){
        }

        @Override
        public void loadClassrooms () {
            classroomInteractor = new ClassroomInteractorImpl();
            classroomInteractor.initiate(this);
        }

        @Override
        public void onLoadClassroomsSuccess (List < Classroom > classrooms) {
            mView.showClassroomsLoaded(classrooms);
        }

        @Override
        public void onLoadClassroomsEror (ExceptionInfo exceptionInfo){
            Toast.makeText(UnibookApplication.getInstance(), exceptionInfo.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
