package com.fargutuvictoria.unibook.network.interactor.executor;

import android.os.Handler;
import android.os.Looper;

public class MainThread {

    private static MainThread INSTANCE;

    private Handler mainHandler;

    private MainThread() {
        // Singleton.
        mainHandler = new Handler(Looper.getMainLooper());
    }

    public static MainThread getInstance() {
        return INSTANCE != null ? INSTANCE : (INSTANCE = new MainThread());
    }

    public void post(Runnable runnable) {
        mainHandler.post(runnable);
    }
}
