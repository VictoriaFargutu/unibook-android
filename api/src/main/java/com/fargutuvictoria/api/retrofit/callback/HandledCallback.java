package com.fargutuvictoria.api.retrofit.callback;

import android.support.annotation.NonNull;

import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class HandledCallback<T> implements Callback<T> {

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            try {
                ResponseBody body = response.errorBody();
                if (body != null) {
                    onError(new Gson().fromJson(body.string(), ExceptionInfo.class));
                } else {
                    onErrorGeneric();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        ExceptionInfo info = new ExceptionInfo();
        info.setMessage(t.getMessage());
        onError(info);
    }

    private void onErrorGeneric() {
        ExceptionInfo info = new ExceptionInfo();
        info.setMessage("Unexpected network error");
        onError(info);
    }

    public abstract void onSuccess(T response);

    public abstract void onError(ExceptionInfo exceptionInfo);
}
