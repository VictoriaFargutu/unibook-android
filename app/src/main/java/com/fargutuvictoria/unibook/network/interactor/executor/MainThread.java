package com.fargutuvictoria.unibook.network.interactor.executor;

import android.os.Handler;
import android.os.Looper;

public class MainThread {

    private Handler mainHandler;

    public MainThread() {
        mainHandler = new Handler(Looper.getMainLooper());
    }

    public void post(Runnable runnable) {
        mainHandler.post(runnable);
    }
}
