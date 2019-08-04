package com.waltercojal.gitflowexample.di.module;

import com.waltercojal.gitflowexample.di.Scope.PerActivity;
import com.waltercojal.gitflowexample.domain.main_interactor.IMainInteractor;
import com.waltercojal.gitflowexample.domain.main_interactor.MainInteractorImpl;
import com.waltercojal.gitflowexample.domain.post_detail_interactor.IPostDetailInteractor;
import com.waltercojal.gitflowexample.domain.post_detail_interactor.PostDetailInteractorImpl;
import com.waltercojal.gitflowexample.network.JsonPlaceHolderApi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import retrofit2.Retrofit;

@Module
public class PresentationModule {

    @PerActivity
    @Provides
    JsonPlaceHolderApi provideJsonPlaceHolderApi(Retrofit retrofit) {
        return retrofit.create(JsonPlaceHolderApi.class);
    }

    @Provides
    IMainInteractor provideMainInteractor(JsonPlaceHolderApi jsonPlaceHolderApi,
                                          @Named("ui_thread") Scheduler uiThread,
                                          @Named("executor_thread") Scheduler executorThread) {
        return new MainInteractorImpl(jsonPlaceHolderApi, uiThread, executorThread);
    }

    @Provides
    IPostDetailInteractor providePostDetailInteractor() {
        return new PostDetailInteractorImpl();
    }

}
