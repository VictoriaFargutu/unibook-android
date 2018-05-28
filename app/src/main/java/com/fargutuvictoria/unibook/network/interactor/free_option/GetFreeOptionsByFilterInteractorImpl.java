package com.fargutuvictoria.unibook.network.interactor.free_option;

import com.fargutuvictoria.api.retrofit.callback.HandledCallback;
import com.fargutuvictoria.api.retrofit.service.FreeOptionService;
import com.fargutuvictoria.commons.model.ExceptionInfo;
import com.fargutuvictoria.commons.model.Filter;
import com.fargutuvictoria.commons.model.FreeOption;
import com.fargutuvictoria.unibook.ApiClient;
import com.fargutuvictoria.unibook.network.interactor.executor.InteractorExecutor;
import com.fargutuvictoria.unibook.network.interactor.executor.MainThread;

import java.util.List;

import retrofit2.Call;

public class GetFreeOptionsByFilterInteractorImpl implements GetFreeOptionsByFilterInteractor {
    private Filter filter;
    private Callback callback;

    @Override
    public void initiate(Filter filter, Callback callback) {
        this.filter = filter;
        this.callback = callback;
        InteractorExecutor.getInstance().run(this);
    }

    @Override
    public void interact() {
        FreeOptionService freeOptionService = ApiClient.getInstance().getRetrofit().create(FreeOptionService.class);
        Call<List<FreeOption>> call = freeOptionService.getAllFreeOptionsByFilter(filter);

        call.enqueue(new HandledCallback<List<FreeOption>>() {
            @Override
            public void onSuccess(List<FreeOption> response) {
                notifySuccess(response);
            }

            @Override
            public void onError(ExceptionInfo exceptionInfo) {
                notifyFailure(exceptionInfo);
            }
        });

    }

    private void notifySuccess(final List<FreeOption> freeOptions) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onGetFreeOptionsSuccess(freeOptions);
            }
        });
    }

    private void notifyFailure(final ExceptionInfo reason) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onGetFreeOptionsError(reason);
            }
        });
    }
}