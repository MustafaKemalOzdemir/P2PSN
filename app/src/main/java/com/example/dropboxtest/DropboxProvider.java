package com.example.dropboxtest;

import android.os.AsyncTask;
import android.os.DropBoxManager;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.android.Auth;
import com.dropbox.core.v2.DbxAppClientV2;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DropboxProvider extends AsyncTask<String,Void,Void> {
    FullAccount account = null;


    public DropboxProvider(){


    }

    @Override
    protected Void doInBackground(String... strings) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();

        DbxClientV2 client = new DbxClientV2(config, Constants.ACCESS_TOKEN);


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

            String json=jsonObject.toString();
            Log.v("Jsontest",json);
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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