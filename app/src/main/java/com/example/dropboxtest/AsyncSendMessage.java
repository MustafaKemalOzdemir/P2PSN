package com.example.dropboxtest;

import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.util.IOUtil;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.WriteMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AsyncSendMessage extends AsyncTask <Void, String,String> {
    DbxClientV2 client;
    String path;
    String message;
    String sender;
    String time;
    ArrayList<MessageSample> sampleArrayList;

    public AsyncSendMessage(DbxClientV2 client,String path,String message,String sender,String time,ArrayList<MessageSample> sampleArrayList){
        this.client=client;
        this.path=path;
        this.message=message;
        this.sender=sender;
        this.time=time;
        this.sampleArrayList=sampleArrayList;

    }


    @Override
    protected String doInBackground(Void... voids) {
        InputStream in;
        StringBuilder result=null;

        try {
            in=client.files().downloadBuilder(path).start().getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            for (String line; (line = r.readLine()) != null; ) {
                result.append(line).append('\n');
            }

        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("asyncDownload",result.toString());
        JSONObject jsonObject=null;
        try {
            jsonObject=new JSONObject(result.toString());
            JSONArray jsonArray=jsonObject.getJSONArray("messages");
            JSONObject messageObject=new JSONObject();
            messageObject.put("message",message);
            messageObject.put("sender",sender);
            messageObject.put("time",time);
            jsonArray.put(messageObject);

            sampleArrayList.clear();
            for(int i=0;i<jsonArray.length();i++){

                //Date date=new Date(Long.getLong(jsonArray.getJSONObject(i).getString("time")));
                //Format format=new SimpleDateFormat("MM dd HH:mm:ss", Locale.ENGLISH);
                //format.format(date)

                MessageSample messageSample=new MessageSample(jsonArray.getJSONObject(i).getString("message"),jsonArray.getJSONObject(i).getString("time"),jsonArray.getJSONObject(i).getString("sender"));
                sampleArrayList.add(messageSample);
            }

            Log.v("asyncDownload",jsonObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }




        String json=jsonObject.toString();
        InputStream byteArrayInputStream=new ByteArrayInputStream(json.getBytes());

        try {
            client.files().uploadBuilder(path).withMode(WriteMode.OVERWRITE).uploadAndFinish(byteArrayInputStream);
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        MessageActivity.notifyAdapter();
    }
}
