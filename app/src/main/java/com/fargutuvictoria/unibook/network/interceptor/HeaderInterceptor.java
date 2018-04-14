package com.fargutuvictoria.unibook.network.interceptor;

import com.fargutuvictoria.unibook.preferences.SharedPreferencesHandler;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        SharedPreferencesHandler mPreferencesHandler = SharedPreferencesHandler.getInstance();
        if (mPreferencesHandler.getSessionToken() != null) {
            request = request.newBuilder()
                    .addHeader("X-Auth-Token", mPreferencesHandler.getSessionToken())
                    .build();

        }
        return chain.proceed(request);
    }
}
