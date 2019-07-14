package com.waltercojal.gitflowexample.domain.post_detail_interactor;

import com.waltercojal.gitflowexample.data.entities.Post;
import com.waltercojal.gitflowexample.network.ApiClient;
import com.waltercojal.gitflowexample.network.JsonPlaceHolderApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailInteractorImpl implements IPostDetailInteractor {

    private int id = -1;

    @Override
    public void getPost(PostDetailCallBack detailCallBack) {

        if (id == -1) {
            detailCallBack.onError("El id no es correcto");
            return;
        }

        JsonPlaceHolderApi placeHolderApi = ApiClient.client().create(JsonPlaceHolderApi.class);
        Call<Post> call = placeHolderApi.getPostDetail(id);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    detailCallBack.onError("Code: " + response.code());
                } else {
                    detailCallBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                detailCallBack.onError(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void setPostId(int id) {
        this.id = id;
    }
}
