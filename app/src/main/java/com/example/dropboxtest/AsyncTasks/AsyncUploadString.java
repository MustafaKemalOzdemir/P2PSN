package com.example.dropboxtest.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.example.dropboxtest.TaskCompleted;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AsyncUploadString extends AsyncTask<Void,String,String> {


    private DbxClientV2 client;
    private String string;
    private String path;
    private TaskCompleted taskCompleted;

    public AsyncUploadString(DbxClientV2 client, String string, String path, TaskCompleted taskCompleted){
        this.client=client;
        this.string=string;
        this.path=path;
        this.taskCompleted=taskCompleted;
    }




    @Override
    protected String doInBackground(Void... voids) {
        InputStream in=new ByteArrayInputStream(string.getBytes());
        String string=null;
        try {
            string=client.files().uploadBuilder(path).uploadAndFinish(in).toString();
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        taskCompleted.onTaskComplete(result);


    }

}