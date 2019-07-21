package com.waltercojal.gitflowexample.di.component;

import com.waltercojal.gitflowexample.di.module.PresentationModule;
import com.waltercojal.gitflowexample.presentation.main.view.MainActivity;
import com.waltercojal.gitflowexample.presentation.post_detail.view.PostDetailActivity;

import dagger.Component;

@Component(modules = PresentationModule.class)
public interface PresentationComponent {
    void inject(MainActivity mainActivity);
    void inject(PostDetailActivity postDetailActivity);
}
