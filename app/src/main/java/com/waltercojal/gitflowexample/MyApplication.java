package com.waltercojal.gitflowexample;

import android.app.Application;
import com.waltercojal.gitflowexample.di.component.ApplicationComponent;
import com.waltercojal.gitflowexample.di.component.DaggerApplicationComponent;
import com.waltercojal.gitflowexample.di.module.ApplicationModule;

public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
    }

    void initializeComponent() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
