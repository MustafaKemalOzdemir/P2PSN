package com.example.dropboxtest.AsyncTasks;

import android.os.AsyncTask;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.example.dropboxtest.Fragments.FragmentHome;
import com.example.dropboxtest.Objects.Group;
import com.example.dropboxtest.Objects.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AsyncUpdatePosts extends AsyncTask<Void,Void,Void> {
   private ArrayList<Group> groups;
   private DbxClientV2 client;
   private ArrayList<Post> posts;
   public AsyncUpdatePosts(DbxClientV2 client,ArrayList<Group> groups,ArrayList<Post> posts){
       this.client=client;
       this.groups=groups;
       this.posts=posts;

   }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        FragmentHome.notifyAdapter();
    }

    @Override
    protected Void doInBackground(Void... voids) {
       StringBuilder builderResult;
        for(int i=0;i<groups.size();i++){
            try {
                InputStream in=client.files().downloadBuilder(groups.get(i).getFolderPath()).start().getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                builderResult = new StringBuilder();
                for (String line; (line = r.readLine()) != null; ) {
                    builderResult.append(line).append('\n');
                }
                JSONObject jsonObject=new JSONObject(builderResult.toString());
                JSONArray jsonArray=jsonObject.getJSONArray("posts");
                Post post;
                for(int k=0;k<jsonArray.length();k++){
                    post=new Post();
                    JSONObject postObject=jsonArray.getJSONObject(i);
                    post.setId(postObject.getString("id"));
                    post.setPostContext(postObject.getString("post-context"));
                    post.setPostPublisher(postObject.getString("post-publisher"));
                    post.setNumberOfComments(postObject.getInt("number-of-comment"));
                    post.setTime(postObject.getString("time"));
                    post.setType(postObject.getString("type"));
                    JSONArray commentArray=postObject.getJSONArray("comments");
                    for(int j=0;j<commentArray.length();j++){
                        post.addElement(commentArray.getString(i));
                    }
                    posts.add(post);

                }
            } catch (DbxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

       return null;
    }
}
