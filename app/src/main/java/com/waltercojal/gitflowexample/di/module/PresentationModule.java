package com.waltercojal.gitflowexample.di.module;

import com.waltercojal.gitflowexample.domain.main_interactor.IMainInteractor;
import com.waltercojal.gitflowexample.domain.main_interactor.MainInteractorImpl;
import com.waltercojal.gitflowexample.domain.post_detail_interactor.IPostDetailInteractor;
import com.waltercojal.gitflowexample.domain.post_detail_interactor.PostDetailInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    @Provides
    IMainInteractor provideMainInteractor() {
        return new MainInteractorImpl();
    }

    @Provides
    IPostDetailInteractor providePostDetailInteractor() {
        return new PostDetailInteractorImpl();
    }

}
