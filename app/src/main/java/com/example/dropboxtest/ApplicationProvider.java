package com.example.dropboxtest;

import android.content.Context;
import android.util.Log;

public class ApplicationProvider {
    private Dropbox2Provider dropbox2Provider;
    public static Friend friend=new Friend();
    public ApplicationProvider(Context context){
        dropbox2Provider=new Dropbox2Provider(context);
    }

    public void addFriend(String email){

        dropbox2Provider.shareFolder(Constants.Friends_Folder_Path+"/User_"+email,friend);
        friend.seteMail(email);
        Log.v("addFriend","Started");
        while (friend.getFolderId()==null){

        }
        Log.v("addFriend",friend.getFolderId());
        dropbox2Provider.addFolderMember(friend.getFolderId(),friend.geteMail());

        dropbox2Provider.uploadString("bu bir deneme scripti",Constants.Friends_Folder_Path+"/User_"+email);


    }

}
