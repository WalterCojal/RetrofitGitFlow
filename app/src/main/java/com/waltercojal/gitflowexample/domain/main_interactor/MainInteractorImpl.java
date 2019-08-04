package com.waltercojal.gitflowexample.domain.main_interactor;

import com.waltercojal.gitflowexample.data.entities.Post;
import com.waltercojal.gitflowexample.network.JsonPlaceHolderApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class MainInteractorImpl implements IMainInteractor {

    private final JsonPlaceHolderApi jsonPlaceHolderApi;
    private final Scheduler uiThread;
    private final Scheduler executorScheduler;

    @Inject
    public MainInteractorImpl(JsonPlaceHolderApi jsonPlaceHolderApi, Scheduler uiThread, Scheduler executorScheduler) {
        this.jsonPlaceHolderApi = jsonPlaceHolderApi;
        this.uiThread = uiThread;
        this.executorScheduler = executorScheduler;
    }

    @Inject



    @Override
    public Observable<List<Post>> getAllPost() {
        return jsonPlaceHolderApi.getPosts()
                .observeOn(uiThread)
                .subscribeOn(executorScheduler);
    }
}
