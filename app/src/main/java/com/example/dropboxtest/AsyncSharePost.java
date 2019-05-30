package com.example.dropboxtest;

import android.os.AsyncTask;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DownloadErrorException;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.users.FullAccount;
import com.example.dropboxtest.Objects.Group;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

public class AsyncSharePost extends AsyncTask<Void,Void,Void> {
    private DbxClientV2 client;
    private ArrayList<Group> groups;
    private String postContext;

    public AsyncSharePost(DbxClientV2 client, ArrayList<Group> groups,String postContext) {
        this.client = client;
        this.groups = groups;
        this.postContext=postContext;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //with photo and Text
        StringBuilder builderResult;

        try {
            FullAccount account=client.users().getCurrentAccount();
            String currentEmail=account.getEmail();
            String id=Calendar.getInstance().getTime().toString()+"-"+account.getAccountId();
            for (int i=0;i<groups.size();i++){

                InputStream in;
                in = client.files().downloadBuilder(groups.get(i).getFolderPath()).start().getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                builderResult = new StringBuilder();
                for (String line; (line = r.readLine()) != null; ) {
                    builderResult.append(line).append('\n');
                }

                JSONObject groupObject=new JSONObject(builderResult.toString());
                JSONArray jsonArray=groupObject.getJSONArray("posts");
                JSONArray comments=new JSONArray();
                JSONObject post=new JSONObject();
                post.put("id",id);
                post.put("post-context", postContext);
                post.put("post-publisher", currentEmail);
                post.put("number-of-comment", 0);
                post.put("comments",comments);
                jsonArray.put(post);

                InputStream input=new ByteArrayInputStream(groupObject.toString().getBytes());
                client.files().uploadBuilder(groups.get(i).getFolderPath()).withMode(WriteMode.OVERWRITE).uploadAndFinish(input);
            }





            return null;
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }
}