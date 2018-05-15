package com.fargutuvictoria.api.retrofit.service;


import com.fargutuvictoria.commons.model.Reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReservationService {
    @GET("/reservations")
    Call<List<Reservation>> getReservationsByUserId(@Query("id") Long id);

    @DELETE("/reservations/{id}")
    Call<Void> cancelReservation(@Path("id") Long id);
}
