package com.waltercojal.gitflowexample.domain.main_interactor;

import com.waltercojal.gitflowexample.data.entities.Post;

import java.util.List;

public interface IMainInteractor {

    interface MainCallBack {
        void onSuccess(List<Post> list);
        void onError(String errorMsg);
    }

    void getAllPost(MainCallBack callBack);

}
