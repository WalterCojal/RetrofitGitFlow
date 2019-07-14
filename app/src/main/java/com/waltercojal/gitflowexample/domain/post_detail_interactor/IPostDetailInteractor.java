package com.waltercojal.gitflowexample.domain.post_detail_interactor;

import com.waltercojal.gitflowexample.data.entities.Post;

public interface IPostDetailInteractor {

    interface PostDetailCallBack {
        void onSuccess(Post post);
        void onError(String errorMsg);
    }

    void getPost(PostDetailCallBack detailCallBack);
    void setPostId(int id);

}
