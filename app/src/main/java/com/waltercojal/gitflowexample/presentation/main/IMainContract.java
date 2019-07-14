package com.waltercojal.gitflowexample.presentation.main;

import com.waltercojal.gitflowexample.data.entities.Post;

import java.util.List;

public interface IMainContract {

    interface IView {
        void showError(String errorMsg);
        void showProgressBar();
        void hideProgressBar();
        void getAllPostSuccess(List<Post> postList);
        void goToDetailPost(int position);
    }

    interface IPresenter {
        void attachView(IView view);
        void detachView();
        boolean isViewAttached();
        void getAllPost();
    }
}
