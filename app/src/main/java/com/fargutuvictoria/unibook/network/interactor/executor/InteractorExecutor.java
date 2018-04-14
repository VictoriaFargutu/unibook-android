package com.fargutuvictoria.unibook.network.interactor.executor;

import com.fargutuvictoria.unibook.network.interactor.Interactor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class InteractorExecutor {

    private static InteractorExecutor INSTANCE;

    private ThreadPoolExecutor threadPoolExecutor;
    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();

    public InteractorExecutor() {
        threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT, WORK_QUEUE);
    }

    public static InteractorExecutor getInstance() {
        return INSTANCE != null ? INSTANCE : (INSTANCE = new InteractorExecutor());
    }

    public void run(final Interactor interactor) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                interactor.interact();
            }
        });
    }
}
