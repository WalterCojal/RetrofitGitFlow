package com.waltercojal.gitflowexample.presentation.main.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.waltercojal.gitflowexample.R;
import com.waltercojal.gitflowexample.data.entities.Post;

class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List<Post> posts;
    private PostClickListener listener;

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView body;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.lp_title);
            body = itemView.findViewById(R.id.lp_body);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.OnClickListener(getAdapterPosition());
                    }
                }
            });
        }
    }

    PostAdapter(List<Post> data) {
        posts = data;
    }

    public void setOnItemClickListener(PostClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.title.setText(post.getTitle());
        holder.body.setText(post.getText());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
