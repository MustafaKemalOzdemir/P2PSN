package com.example.dropboxtest;

import android.os.AsyncTask;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.UploadSessionCursor;

public class AsyncShareFolder extends AsyncTask<Void, String,String> {
    DbxClientV2 client;
    String path;

    public AsyncShareFolder(DbxClientV2 client,String path){
        this.client=client;
        this.path=path;
    }
    @Override
    protected String doInBackground(Void... voids) {
        try {
            client.sharing().shareFolderBuilder(path).start();
        } catch (DbxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
