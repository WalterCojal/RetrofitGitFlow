package com.waltercojal.gitflowexample.domain.main_interactor;

import com.waltercojal.gitflowexample.data.entities.Post;

import java.util.List;

import io.reactivex.Observable;

public interface IMainInteractor {

    Observable<List<Post>> getAllPost();

}
