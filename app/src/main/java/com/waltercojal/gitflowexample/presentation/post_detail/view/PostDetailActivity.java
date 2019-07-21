package com.waltercojal.gitflowexample.presentation.post_detail.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.waltercojal.gitflowexample.MyApplication;
import com.waltercojal.gitflowexample.R;
import com.waltercojal.gitflowexample.data.entities.Post;
import com.waltercojal.gitflowexample.presentation.post_detail.IPostDetailPresenter;
import com.waltercojal.gitflowexample.presentation.post_detail.presenter.PostDetailPresenter;

import javax.inject.Inject;

public class PostDetailActivity extends AppCompatActivity implements IPostDetailPresenter.IView {

    private TextView main;
    private ProgressBar progressBar;
    @Inject
    PostDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        MyApplication application = (MyApplication) getApplication();
        application.getAppComponent().inject(this);
        // presenter = new PostDetailPresenter(new PostDetailInteractorImpl());
        presenter.attachView(this);
        main = findViewById(R.id.postDetailText);
        progressBar = findViewById(R.id.postDetailProgress);
        int id = getIntent().getIntExtra("post_id", -1);
        presenter.getPost(id);
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
    public void getPostSuccess(Post post) {
        main.setText("userId: " + post.getUserId() + "\n" +
                "id: " + post.getId() + "\n" +
                "title: " + post.getTitle() + "\n" +
                "body: " + post.getText());
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onDetachedFromWindow() {
        presenter.detachView();
        super.onDetachedFromWindow();
    }
}
