package com.waltercojal.gitflowexample.presentation.main.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.waltercojal.gitflowexample.R;
import com.waltercojal.gitflowexample.data.entities.Post;
import com.waltercojal.gitflowexample.domain.main_interactor.MainInteractorImpl;
import com.waltercojal.gitflowexample.network.JsonPlaceHolderApi;
import com.waltercojal.gitflowexample.presentation.main.IMainContract;
import com.waltercojal.gitflowexample.presentation.main.presenter.MainPresenter;
import com.waltercojal.gitflowexample.presentation.post_detail.view.PostDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainContract.IView {

    private RecyclerView result;
    private PostAdapter adapter;
    private ProgressBar progressBar;
    private List<Post> postList = new ArrayList<>();
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(new MainInteractorImpl());
        mainPresenter.attachView(this);
        result = findViewById(R.id.result);
        progressBar = findViewById(R.id.main_progress);
        result.setLayoutManager(new LinearLayoutManager(this));
        result.setItemAnimator(new DefaultItemAnimator());
        adapter = new PostAdapter(postList);
        adapter.setOnItemClickListener(this::goToDetailPost);
        result.setAdapter(adapter);
        mainPresenter.getAllPost();
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getAllPostSuccess(List<Post> postList) {
        this.postList.addAll(postList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void goToDetailPost(int position) {
        Intent intent = new Intent(this, PostDetailActivity.class);
        intent.putExtra("post_id", postList.get(position).getId());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        mainPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onDetachedFromWindow() {
        mainPresenter.detachView();
        super.onDetachedFromWindow();
    }
}
