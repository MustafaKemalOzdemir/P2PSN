package com.example.dropboxtest.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

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
        Log.v("refreshPosts",posts.size()+" =Posts Size");
    }

    @Override
    protected Void doInBackground(Void... voids) {
       StringBuilder builderResult;
       posts.clear();
        Log.v("refreshPosts",groups.size()+" =Groups at Async Size");
        for(int i=0;i<groups.size();i++){
            try {
                InputStream in=client.files().downloadBuilder(groups.get(i).getFolderPath()+"/Group.txt").start().getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                builderResult = new StringBuilder();
                for (String line; (line = r.readLine()) != null; ) {
                    builderResult.append(line).append('\n');
                }
                JSONObject jsonObject=new JSONObject(builderResult.toString());
                JSONArray jsonArray=jsonObject.getJSONArray("posts");
                Post post;
                Log.v("refreshPosts",jsonArray.length()+ "Async update jsonarray lenght");
                Log.v("refreshPosts",builderResult.toString());
                for(int k=0;k<jsonArray.length();k++){
                    post=new Post();
                    JSONObject postObject=jsonArray.getJSONObject(k);
                    post.setId(postObject.getString("id"));
                    post.setPostContext(postObject.getString("post-context"));
                    post.setPostPublisher(postObject.getString("post-publisher"));
                    post.setNumberOfComments(postObject.getInt("number-of-comment"));
                    post.setTime(postObject.getString("time"));
                    post.setType(postObject.getString("type"));
                    post.setPostPath(postObject.getString("post-path"));
                    JSONArray commentArray=postObject.getJSONArray("comments");
                    Log.v("refreshPosts",commentArray.length()+" =commentArrayLength");
                    if(commentArray.length()!=0){
                        for(int j=0;j<commentArray.length();j++){
                            JSONObject commentObject=commentArray.getJSONObject(j);
                            post.addElement(commentObject.getString("name"), commentObject.getString("comment-text"));
                        }
                    }

                    posts.add(post);

                }
            } catch (DbxException e) {
                e.printStackTrace();
                Log.v("refreshPosts", "Error Dbx="+ e.getMessage() );
            } catch (IOException e) {
                e.printStackTrace();
                Log.v("refreshPosts", "Error Io="+ e.getMessage() );
            } catch (JSONException e) {
                e.printStackTrace();
                Log.v("refreshPosts", "Error Json="+ e.getMessage() );

            }
        }

       return null;
    }
}
