package com.fargutuvictoria.api.retrofit.service;


import com.fargutuvictoria.commons.model.FreeOption;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FreeOptionService {
    @GET("/freeOptions/classrooms")
    Call<List<FreeOption>> getAllFreeOptionsByClassroom(@Query("classroomId") Long classroomId);
}
