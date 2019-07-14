package com.waltercojal.gitflowexample.presentation.post_detail;

import com.waltercojal.gitflowexample.data.entities.Post;

public interface IPostDetailPresenter {

    interface IView {
        void showError(String errorMsg);
        void showProgressBar();
        void hideProgressBar();
        void getPostSuccess(Post post);
    }

    interface IPresenter {
        void attachView(IView view);
        void detachView();
        boolean isViewAttached();
        void getPost(int id);
    }

}
