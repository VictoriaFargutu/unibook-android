package com.fargutuvictoria.api.retrofit.service;

import com.fargutuvictoria.commons.model.AuthSession;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthService {

    @POST("/auth/login")
    Call<AuthSession> login(@Header("Authorization") String auth);

    @GET("/auth/validation")
    Call<AuthSession> validateToken();

    @POST("/auth/logout")
    Call<Void> logout();
}
