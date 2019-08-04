package com.waltercojal.gitflowexample.network;

import com.waltercojal.gitflowexample.data.entities.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Observable<List<Post>> getPosts();

    @GET("posts/{id}")
    Observable<Post> getPostDetail(@Path("id") int id);

}
