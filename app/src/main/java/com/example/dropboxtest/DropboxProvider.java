package com.example.dropboxtest;

import android.os.AsyncTask;
import android.os.DropBoxManager;
import android.util.JsonWriter;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxUploader;
import com.dropbox.core.android.Auth;
import com.dropbox.core.v2.DbxAppClientV2;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.users.FullAccount;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class DropboxProvider extends AsyncTask<String,Void,Void> {
    FullAccount account = null;


    public DropboxProvider(){


    }

    @Override
    protected Void doInBackground(String... strings) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();

        DbxClientV2 client = new DbxClientV2(config, Constants.ACCESS_TOKEN);
        String json="";


        DbxUploader








        try {

            account = client.users().getCurrentAccount();
            //Json test
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("Count",4);
            jsonObject.put("name","mk");
            JSONArray array=new JSONArray();
            array.put("mk");
            array.put("hatce");
            array.put("yumt");
            jsonObject.put("array", array);

            json=jsonObject.toString();

            Log.v("Jsontest",json);
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter("test.txt")) {
            out.println(json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (InputStream in = new FileInputStream("test.txt")) {

            FileMetadata metadata = client.files().uploadBuilder("/test.txt")
                    .uploadAndFinish(in);
        } catch (UploadErrorException e) {
            e.printStackTrace();
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.v("mTest",account.getName().getDisplayName());
        Log.v("mTest",account.getLocale());
        Log.v("mTest",account.getEmail());
        Log.v("mTest","finished");
    }
}