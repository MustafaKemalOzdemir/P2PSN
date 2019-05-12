package com.example.dropboxtest;

import android.content.Context;
import android.util.Log;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.example.dropboxtest.AsyncTasks.AsyncAddFolderMember;
import com.example.dropboxtest.AsyncTasks.AsyncShareFolder;

import org.json.JSONException;
import org.json.JSONObject;

public class ApplicationProvider {
    private Dropbox2Provider dropbox2Provider;

    private DbxClientV2 client;
    private DbxRequestConfig config;
    private Context context;

    public static Friend friend=new Friend();
    public ApplicationProvider(Context context){
        this.config = DbxRequestConfig.newBuilder("dropbox").build();
        this.client = new DbxClientV2(config, Constants.ACCESS_TOKEN);
        this.context=context;

    }

    public void addFriend(String email){
        dropbox2Provider.shareFolder(Constants.Friends_Folder_Path+"/User_"+email);
        friend.seteMail(email);
        Log.v("addFriend","Called");


        Log.v("addFriend","while finished");
        //Log.v("addFriend",friend.getFolderId());
        //dropbox2Provider.addFolderMember(friend.getFolderId(),friend.geteMail());

        //dropbox2Provider.uploadString("bu bir deneme scripti",Constants.Friends_Folder_Path+"/User_"+email);


    }
    public void addFriend2(final String email){
        String path=Constants.Friends_Folder_Path+"/User_"+email;
        AsyncAddFriend asyncAddFriend=new AsyncAddFriend(client,context,path,email);
        asyncAddFriend.execute();

    }
    public void sendMessage(String path, String message){
        AsyncSendMessage asyncSendMessage=new AsyncSendMessage(client,path,message);
        asyncSendMessage.execute();
    }

}
