package com.example.dropboxtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dropboxtest.Objects.Comment;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    class CommentViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView commentText;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            commentText=itemView.findViewById(R.id.commentCommentText);
            name=itemView.findViewById(R.id.commentUseName);
        }
    }
    ArrayList<Comment> comments;
    public CommentAdapter(ArrayList<Comment> comments){
        this.comments=comments;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_list_item,viewGroup,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder commentViewHolder, int i) {
        commentViewHolder.name.setText(comments.get(i).getName());
        commentViewHolder.commentText.setText(comments.get(i).getCommentText());

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }






}

