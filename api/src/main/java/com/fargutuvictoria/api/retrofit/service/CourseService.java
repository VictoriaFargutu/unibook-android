package com.fargutuvictoria.api.retrofit.service;

import com.fargutuvictoria.commons.model.AuthSession;
import com.fargutuvictoria.commons.model.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CourseService {

    @GET("api/courses/all")
    Call<List<Course>> getAllCourses();
}
