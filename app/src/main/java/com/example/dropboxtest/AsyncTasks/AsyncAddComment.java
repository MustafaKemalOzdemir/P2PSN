package com.example.dropboxtest.AsyncTasks;

import android.os.AsyncTask;

import com.dropbox.core.v2.DbxClientV2;
import com.example.dropboxtest.Objects.Post;

public class AsyncAddComment extends AsyncTask<Void,Void,Void> {
    private  DbxClientV2 client;
    private  String commentText;
    private Post post;

    public AsyncAddComment(DbxClientV2 client,String commentText,Post post){
       this.client=client;
       this.commentText=commentText;
       this.post=post;
    }

    @Override
    protected Void doInBackground(Void... voids) {


        return null;
    }
}
