package com.example.dropboxtest;

import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Dropbox2Provider {
    DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    DbxClientV2 client = new DbxClientV2(config, Constants.ACCESS_TOKEN);
    public Metadata mData;






    public void uploadString(String string, String path){
        AsyncUploadString asyncUploadString=new AsyncUploadString(client, string, path);
        asyncUploadString.execute();
    }

}

