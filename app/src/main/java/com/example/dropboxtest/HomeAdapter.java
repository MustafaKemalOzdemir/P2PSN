package com.example.dropboxtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dropboxtest.Objects.Post;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    class TextPostViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        TextView date;
        TextView content;
        ImageView imageView;


        public TextPostViewHolder(@NonNull View itemView) {
            super(itemView);
            userName=itemView.findViewById(R.id.name);
            date=itemView.findViewById(R.id.date);
            content=itemView.findViewById(R.id.status);
            imageView=itemView.findViewById(R.id.image);
        }
    }
    class PhotoPostViewHolder extends RecyclerView.ViewHolder{

        public PhotoPostViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    private ArrayList<Post> posts;
    public HomeAdapter(ArrayList<Post> posts){
        this.posts=posts;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;

        switch(i){
            case 1:
                view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_list_status_item,viewGroup,false);
                return new HomeAdapter.TextPostViewHolder(view);
            case 2: Log.v("sendMessage","Receiver Created");
                view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_list_photos_item,viewGroup,false);

                return new HomeAdapter.PhotoPostViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type=viewHolder.getItemViewType();
        if(type==1){
            ((TextPostViewHolder)viewHolder).userName.setText(posts.get(i).getPostPublisher());
            ((TextPostViewHolder)viewHolder).content.setText(posts.get(i).getPostContext());
            Date date=new Date(Long.parseLong(posts.get(i).getTime()));
            Format format=new SimpleDateFormat("MM dd - HH:mm", Locale.ENGLISH);
            ((TextPostViewHolder)viewHolder).date.setText(format.format(date));
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        String type=posts.get(position).getType();
        if(type.equals("1")){
            return 1;
        }else{
            return 2;
        }
    }
}
