package com.fargutuvictoria.unibook.di.module;

import com.fargutuvictoria.api.retrofit.service.AuthService;
import com.fargutuvictoria.commons.preferences.SharedPreferencesHandler;
import com.fargutuvictoria.unibook.network.interceptor.HeaderInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String BASE_URL = "http://192.168.1.5:2020"; //Ip local

    @Provides
    @Singleton
    public Retrofit provideRetrofit(HeaderInterceptor headerInterceptor) {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder()
                        .addNetworkInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY))
                        .addInterceptor(headerInterceptor)
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public AuthService provideAuthService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }

    @Provides
    @Singleton
    public HeaderInterceptor provideHeaderInterceptor(SharedPreferencesHandler sharedPreferencesHandler) {
        return new HeaderInterceptor(sharedPreferencesHandler);
    }


}
