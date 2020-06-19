package com.example.dropboxtest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DropBox_Service extends Service {
    FullAccount account = null;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
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


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("mTest",account.getName().getDisplayName());
        Log.v("mTest",account.getLocale());
        Log.v("mTest",account.getEmail());
        Log.v("mTest","finished");
    }
}
