package com.waltercojal.gitflowexample.domain.main_interactor;

import com.waltercojal.gitflowexample.data.entities.Post;
import com.waltercojal.gitflowexample.network.ApiClient;
import com.waltercojal.gitflowexample.network.JsonPlaceHolderApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractorImpl implements IMainInteractor {

    @Override
    public void getAllPost(MainCallBack callBack) {
        JsonPlaceHolderApi placeHolderApi = ApiClient.client().create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = placeHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    callBack.onError("Code: " + response.code());
                } else {
                    callBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                callBack.onError(t.getLocalizedMessage());
            }
        });
    }
}
