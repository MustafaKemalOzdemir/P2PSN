package com.example.dropboxtest.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.WriteMode;
import com.example.dropboxtest.Constants;
import com.example.dropboxtest.Objects.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        String path=post.getPostPath();
        String id=post.getId();
        StringBuilder builderResult;
        Log.v("AddComment",path);

        InputStream in;
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
                    JSONObject comment=new JSONObject();
                    comment.put("name", client.users().getCurrentAccount().getName().getDisplayName());
                    comment.put("comment-text",commentText);
                    comments.put(comment);
                    break;

                }
            }
            InputStream inputStream=new ByteArrayInputStream(jsonObject.toString().getBytes());
            client.files().uploadBuilder(path).withMode(WriteMode.OVERWRITE).uploadAndFinish(inputStream);
        } catch (DbxException e) {
            e.printStackTrace();
            Log.v("AddComment","Dbx Error="+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("AddComment","IO Error="+e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.v("AddComment","Json Error="+e.getMessage());
        }


        return null;
    }
}
