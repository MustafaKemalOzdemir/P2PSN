package com.example.dropboxtest;

import android.content.Context;

public class ApplicationProvider {
    Dropbox2Provider dropbox2Provider;
    public ApplicationProvider(Context context){
        dropbox2Provider=new Dropbox2Provider(context);
    }

    public void addFriend(String email){
        dropbox2Provider.createFolder(Constants.Friends_File_Path+"/User_"+email,false);
        dropbox2Provider.uploadString("bu bir deneme scripti",Constants.Friends_File_Path+"/User_"+email);
        //dropbox2Provider.sh

    }
}
