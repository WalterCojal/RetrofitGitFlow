package com.waltercojal.gitflowexample.presentation.post_detail.presenter;

import com.waltercojal.gitflowexample.data.entities.Post;
import com.waltercojal.gitflowexample.domain.post_detail_interactor.IPostDetailInteractor;
import com.waltercojal.gitflowexample.presentation.post_detail.IPostDetailPresenter;

import javax.inject.Inject;

public class PostDetailPresenter implements IPostDetailPresenter.IPresenter {

    IPostDetailPresenter.IView view;
    private final IPostDetailInteractor postDetailInteractor;

    @Inject
    public PostDetailPresenter(IPostDetailInteractor iPostDetailInteractor) {
        this.postDetailInteractor = iPostDetailInteractor;
    }

    @Override
    public void attachView(IPostDetailPresenter.IView view) {
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
    public void getPost(int id) {
        view.showProgressBar();
        postDetailInteractor.setPostId(id);
        postDetailInteractor.getPost(new IPostDetailInteractor.PostDetailCallBack() {
            @Override
            public void onSuccess(Post post) {
                if (isViewAttached()) {
                    view.getPostSuccess(post);
                    view.hideProgressBar();
                }
            }

            @Override
            public void onError(String errorMsg) {
                if (isViewAttached()) {
                    view.hideProgressBar();
                    view.showError(errorMsg);
                }
            }
        });
    }
}
