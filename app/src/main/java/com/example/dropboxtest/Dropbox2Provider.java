package com.example.dropboxtest;

import android.content.Context;
import android.util.Log;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.example.dropboxtest.AsyncTasks.AsyncAddFolderMember;
import com.example.dropboxtest.AsyncTasks.AsyncCreateDirectory;
import com.example.dropboxtest.AsyncTasks.AsyncListFolders;
import com.example.dropboxtest.AsyncTasks.AsyncShareFolder;
import com.example.dropboxtest.AsyncTasks.AsyncUploadString;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Dropbox2Provider{
    DbxRequestConfig config;
    DbxClientV2 client;
    Context context;

    public Dropbox2Provider(Context context){
        config = DbxRequestConfig.newBuilder("dropbox").build();
        client = new DbxClientV2(config, Constants.ACCESS_TOKEN);
        this.context=context;

    }

    public void uploadString(String string, String path){
        AsyncUploadString asyncUploadString=new AsyncUploadString(client, string, path, new TaskCompleted() {
            @Override
            public void onTaskComplete(String result) {
                Log.v("TaskTest",result);
            }
        });
        asyncUploadString.execute();
    }
    public void createFolder(String path, boolean autoRename){
        AsyncCreateDirectory asyncCreateDirectory=new AsyncCreateDirectory(client, path, autoRename, new TaskCompleted() {
            @Override
            public void onTaskComplete(String result) {
                Log.v("TaskTest2",result);
            }
        });
        asyncCreateDirectory.execute();

    }
    public void listFolder(String path){
        AsyncListFolders asyncListFolders=new AsyncListFolders(client, path, new TaskCompleted() {
            @Override
            public void onTaskComplete(String result) {
                Log.v("TaskTest3",result);

            }
        });
        asyncListFolders.execute();
    }
    public void shareFolder(String path, final Friend friend){

        AsyncShareFolder asyncShareFolder=new AsyncShareFolder(client, path, new TaskCompleted() {
            //Error There
            @Override
            public void onTaskComplete(String result) {
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String sharedFolderId=jsonObject.getString("shared_folder_id");
                    friend.setFolderId(sharedFolderId);
                    Log.v("addFriend","folderid= "+friend.getFolderId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.v("TaskTest4",result);
            }
        },context);
        asyncShareFolder.execute();
    }
    public void addFolderMember(String folderId,String eMail){
        AsyncAddFolderMember asyncAddFolderMember=new AsyncAddFolderMember(client,folderId,eMail);
        asyncAddFolderMember.execute();

    }




}

