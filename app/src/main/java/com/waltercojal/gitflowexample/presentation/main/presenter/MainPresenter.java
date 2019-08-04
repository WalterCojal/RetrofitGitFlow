package com.waltercojal.gitflowexample.presentation.main.presenter;

import com.waltercojal.gitflowexample.data.entities.Post;
import com.waltercojal.gitflowexample.domain.main_interactor.IMainInteractor;
import com.waltercojal.gitflowexample.presentation.main.IMainContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainPresenter implements IMainContract.IPresenter {

    IMainContract.IView view;
    private final IMainInteractor interactor;
    private Disposable disposable;

    @Inject
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
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void getAllPost() {
        view.showProgressBar();
        interactor.getAllPost().subscribe(new Observer<List<Post>>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(List<Post> posts) {
                if (isViewAttached()) {
                    view.getAllPostSuccess(posts);
                    view.hideProgressBar();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    view.showError(e.getMessage());
                    view.hideProgressBar();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
