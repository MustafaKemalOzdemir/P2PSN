package com.example.dropboxtest;

import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AsyncUploadString extends AsyncTask<Void,Metadata,Metadata> {


    public DbxClientV2 client;
    public String string;
    public String path;

    public AsyncUploadString(DbxClientV2 client,String string,String path){
        this.client=client;
        this.string=string;
        this.path=path;
    }




    @Override
    protected Metadata doInBackground(Void... voids) {
        Log.v("whileTest","started");
        InputStream in=new ByteArrayInputStream(string.getBytes());
        FileMetadata metadata=null;
        try {
            metadata=client.files().uploadBuilder(path).uploadAndFinish(in);
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("whileTest",metadata.toString());
        return metadata;
    }

    @Override
    protected void onPostExecute(Metadata aMetadata) {
        super.onPostExecute(aMetadata);
        Log.v("whileTest","finished");
        return;

    }

}
