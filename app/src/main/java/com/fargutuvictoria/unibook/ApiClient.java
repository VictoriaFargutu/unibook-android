package com.fargutuvictoria.unibook;

import com.fargutuvictoria.commons.DateDeserializer;
import com.fargutuvictoria.unibook.network.interceptor.HeaderInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiClient INSTANCE;

    private static final String BASE_URL = "http://192.168.1.4:2020"; //Ip local

    private Retrofit retrofit;

    public static ApiClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApiClient();
            INSTANCE.retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(new OkHttpClient.Builder()
                            .addNetworkInterceptor(new HttpLoggingInterceptor()
                                    .setLevel(HttpLoggingInterceptor.Level.BODY))
                            .addInterceptor(new HeaderInterceptor())
                            .build())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create()))
                    .build();
        }
        return INSTANCE;
    }

    private ApiClient() {
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
