package com.fargutuvictoria.api.retrofit.service;

import com.fargutuvictoria.commons.model.Classroom;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fargutuvictoria on 05/05/2018.
 */

public interface ClassroomService {
    @GET("/classrooms/course")
    Call<List<Classroom>> getAllCourseClassrooms();

}
