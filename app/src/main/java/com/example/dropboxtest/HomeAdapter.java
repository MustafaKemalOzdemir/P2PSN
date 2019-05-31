package com.example.dropboxtest;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dropboxtest.Activities.CommentActivity;
import com.example.dropboxtest.Objects.Comment;
import com.example.dropboxtest.Objects.Post;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    class TextPostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView userName;
        TextView date;
        TextView content;
        ImageView imageView;
        View button;
        OnItemClickListener onItemClickListener;


        public TextPostViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            userName=itemView.findViewById(R.id.name);
            date=itemView.findViewById(R.id.date);
            content=itemView.findViewById(R.id.status);
            imageView=itemView.findViewById(R.id.image);
            button= itemView.findViewById(R.id.buttonStatusComment);
            this.onItemClickListener=onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id=v.getId();
            int layoutPosition=this.getAdapterPosition();

            Log.v("CommentSection","Clicked");
            if(id==R.id.buttonStatusComment){
                Log.v("CommentSection","clicked statusComment at "+layoutPosition);
            }

        }
    }
    class PhotoPostViewHolder extends RecyclerView.ViewHolder{

        public PhotoPostViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    private OnItemClickListener ClickListener;
    private ArrayList<Post> posts;
    private Activity activity;
    public HomeAdapter(ArrayList<Post> posts,Activity activity, OnItemClickListener onItemClickListener){
        this.posts=posts;
        this.ClickListener=onItemClickListener;
        this.activity=activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;

        switch(i){
            case 1:
                view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_list_status_item,viewGroup,false);
                return new HomeAdapter.TextPostViewHolder(view, ClickListener);
            case 2: Log.v("sendMessage","Receiver Created");
                view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_list_photos_item,viewGroup,false);

                return new HomeAdapter.PhotoPostViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        int type=viewHolder.getItemViewType();
        final int adapterPosition=i;
        if(type==1){
            ((TextPostViewHolder)viewHolder).userName.setText(posts.get(i).getPostPublisher());
            ((TextPostViewHolder)viewHolder).content.setText(posts.get(i).getPostContext());
            Date date=new Date(Long.parseLong(posts.get(i).getTime()));
            Format format=new SimpleDateFormat("MM dd - HH:mm", Locale.ENGLISH);
            ((TextPostViewHolder)viewHolder).date.setText(format.format(date));
            ((TextPostViewHolder)viewHolder).button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("CommentSection","bana clicked");
                    Intent intent=new Intent(activity,CommentActivity.class);
                    Post post=posts.get(adapterPosition);
                    ArrayList<Comment> comments=post.getComments();
                    ArrayList<String> commentNames=new ArrayList<>();
                    ArrayList<String> commentTexts=new ArrayList<>();
                    for(int i=0;i<comments.size();i++){
                        commentNames.add(i,comments.get(i).getName());
                        commentTexts.add(i,comments.get(i).getCommentText());

                    }

                    //intent.putExtra("postNames",posts.get(adapterPosition));
                    intent.putStringArrayListExtra("postNames",commentNames);
                    intent.putStringArrayListExtra("postComments",commentTexts);
                    intent.putExtra("postPath",post.getPostPath());
                    intent.putExtra("id",post.getId());


                    activity.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public int getItemViewType(int position) {
        //Not working
        String type=posts.get(position).getType();
        if(type.equals("1")){
            return 1;
        }else{
            return 2;
        }
    }
}
