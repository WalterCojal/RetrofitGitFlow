package com.waltercojal.gitflowexample;

import android.app.Application;

import com.waltercojal.gitflowexample.di.component.DaggerPresentationComponent;
import com.waltercojal.gitflowexample.di.component.PresentationComponent;
import com.waltercojal.gitflowexample.di.module.PresentationModule;

public class MyApplication extends Application {

    private PresentationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
    }

    void initializeComponent() {
        appComponent = DaggerPresentationComponent.builder()
                .presentationModule(new PresentationModule())
                .build();
    }

    public PresentationComponent getAppComponent() {
        return appComponent;
    }
}
