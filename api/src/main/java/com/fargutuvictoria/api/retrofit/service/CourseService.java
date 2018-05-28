package com.fargutuvictoria.api.retrofit.service;

import com.fargutuvictoria.commons.model.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CourseService {

    @GET("api/courses/all")
    Call<List<Course>> getAllCourses();

    @GET("api/courses/{id}")
    Call<List<Course>> getMyCourses(@Path("id") Long id);
}
