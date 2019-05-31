package com.example.dropboxtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
    private Context context;
    private String type;
    ProgressDialog progressDialog;

    public AsyncSharePost(DbxClientV2 client, ArrayList<Group> groups, String postContext,Context context,String type) {
        this.client = client;
        this.groups = groups;
        this.postContext=postContext;
        this.context=context;
        this.type=type;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.setTitle("Sharing");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
    }



    @Override
    protected Void doInBackground(Void... voids) {
        //with photo and Text
        StringBuilder builderResult;
        Log.v("SharePost","Share post Started");
        Log.v("SharePost","Groups Size"+groups.size());

        try {
            FullAccount account=client.users().getCurrentAccount();
            String currentName=account.getName().getDisplayName();
            String id=Calendar.getInstance().getTime().getTime()+"-"+account.getAccountId();
            for (int i=0;i<groups.size();i++){

                InputStream in;
                Log.v("SharePost","Groups path "+groups.get(i).getFolderPath()+"/Group.txt");
                in = client.files().downloadBuilder(groups.get(i).getFolderPath()+"/Group.txt").start().getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                builderResult = new StringBuilder();
                for (String line; (line = r.readLine()) != null; ) {
                    builderResult.append(line).append('\n');
                }

                JSONObject groupObject=new JSONObject(builderResult.toString());
                JSONArray jsonArray=groupObject.getJSONArray("posts");
                JSONArray comments=new JSONArray();
                JSONObject post=new JSONObject();
                Log.v("Share Post",postContext);

                post.put("id",id);
                post.put("post-context", postContext);
                post.put("post-publisher", currentName);
                post.put("number-of-comment", 0);
                post.put("comments",comments);
                post.put("time",Calendar.getInstance().getTime().getTime()+"");
                post.put("type",type);
                post.put("post-path",groups.get(i).getFolderPath()+"/Group.txt");
                jsonArray.put(post);

                InputStream input=new ByteArrayInputStream(groupObject.toString().getBytes());
                client.files().uploadBuilder(groups.get(i).getFolderPath()+"/Group.txt").withMode(WriteMode.OVERWRITE).uploadAndFinish(input);
            }





            return null;
        }catch (DownloadErrorException e){
            Log.v("SharePost","Error "+e.getMessage());

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