package com.example.dropboxtest;

import android.os.AsyncTask;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;

public class AsyncListFolders extends AsyncTask<Void,String,String> {
    DbxClientV2 client;
    String path;
    TaskCompleted taskCompleted;
    public AsyncListFolders(DbxClientV2 client, String path,TaskCompleted taskCompleted){
        this.client=client;
        this.path=path;
        this.taskCompleted=taskCompleted;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result="";
        try {
            result=client.files().listFolder(path).toString();
        } catch (DbxException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        taskCompleted.onTaskComplete(result);
}
}
