package com.waltercojal.gitflowexample.presentation.main.presenter;

import com.waltercojal.gitflowexample.data.entities.Post;
import com.waltercojal.gitflowexample.domain.main_interactor.IMainInteractor;
import com.waltercojal.gitflowexample.presentation.main.IMainContract;

import java.util.List;

public class MainPresenter implements IMainContract.IPresenter {

    IMainContract.IView view;
    IMainInteractor interactor;

    public MainPresenter(IMainInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IMainContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void getAllPost() {
        view.showProgressBar();
        interactor.getAllPost(new IMainInteractor.MainCallBack() {
            @Override
            public void onSuccess(List<Post> list) {
                if (isViewAttached()) {
                    view.getAllPostSuccess(list);
                    view.hideProgressBar();
                }
            }

            @Override
            public void onError(String errorMsg) {
                if (isViewAttached()) {
                    view.showError(errorMsg);
                    view.hideProgressBar();
                }
            }
        });
    }
}
