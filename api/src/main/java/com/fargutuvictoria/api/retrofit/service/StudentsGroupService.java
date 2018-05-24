package com.fargutuvictoria.api.retrofit.service;


import com.fargutuvictoria.commons.model.StudentsGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StudentsGroupService {
    @GET("/api/group")
    Call<List<StudentsGroup>> getAllStudentsGroups();
}
