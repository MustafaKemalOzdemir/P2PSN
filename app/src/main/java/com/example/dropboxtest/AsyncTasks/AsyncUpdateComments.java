package com.example.dropboxtest.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.example.dropboxtest.Activities.CommentActivity;
import com.example.dropboxtest.Objects.Comment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AsyncUpdateComments extends AsyncTask<Void,Void,Void> {
    private String id;
    private String path;
    private DbxClientV2 client;
    private ArrayList<Comment> currentComments;

    public AsyncUpdateComments(DbxClientV2 client,String id,String path,ArrayList<Comment> currentComments){
        this.id=id;
        this.client=client;
        this.path=path;
        this.currentComments=currentComments;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.v("ShowComment","finished");
        CommentActivity.notifyAdapter();
        //for(int i=0;i<currentComments.size();i++){
         //   Log.v("ShowComment",currentComments.get(i).getCommentText());
        //}
    }

    @Override
    protected Void doInBackground(Void... voids) {
        InputStream in;
        StringBuilder builderResult;
        currentComments.clear();
        try {
            in=client.files().downloadBuilder(path).start().getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            builderResult = new StringBuilder();
            for (String line; (line = r.readLine()) != null; ) {
                builderResult.append(line).append('\n');
            }
            JSONObject jsonObject=new JSONObject(builderResult.toString());
            JSONArray postList=jsonObject.getJSONArray("posts");
            JSONObject post;
            for(int i=0;i<postList.length();i++){
                post=postList.getJSONObject(i);
                Log.v("AddComment",post.toString());
                if(post.getString("id").equals(id)){
                    JSONArray comments=post.getJSONArray("comments");
                    for(int p=0;p<comments.length();p++){
                        JSONObject comment=comments.getJSONObject(p);
                        Comment commentForArray=new Comment();
                        commentForArray.setName(comment.getString("name"));
                        commentForArray.setCommentText(comment.getString("comment-text"));
                        currentComments.add(commentForArray);

                    }

                    break;

                }
            }
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
