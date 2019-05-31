package com.example.dropboxtest.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DownloadErrorException;
import com.example.dropboxtest.Constants;
import com.example.dropboxtest.Objects.Friend;
import com.example.dropboxtest.Objects.Group;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AsyncUpdateGroups extends AsyncTask<Void, Void,Void> {
    private DbxClientV2 client;

    public AsyncUpdateGroups(DbxClientV2 client){
        this.client=client;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        StringBuilder builderResult=null;
        try {
            InputStream in;
            in=client.files().downloadBuilder(Constants.Personal_Groups_Folder_path).start().getInputStream();
            Constants.User=client.users().getCurrentAccount().getName().getDisplayName();

            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            builderResult = new StringBuilder();
            for (String line; (line = r.readLine()) != null; ) {
                builderResult.append(line).append('\n');
            }

        } catch (DownloadErrorException e){
            Log.v("DownloadPhase",e.errorValue.toString());

        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Group> mGroupList=new ArrayList<>();
        try{
            JSONObject jsonObject= new JSONObject(builderResult.toString());
            Log.v("GroupCheck",builderResult.toString());
            JSONArray groupList=jsonObject.getJSONArray("groups");
            JSONObject groupElement;
            Group group;
            for(int i=0;i<groupList.length();i++){
                group=new Group();
                groupElement=groupList.getJSONObject(i);
               // group.setFolderId(groupElement.getString("folderId"));
                group.setFolderPath(groupElement.getString("groupPath"));
                group.setGroupName(groupElement.getString("groupName"));
                mGroupList.add(group);

            }
            if(!mGroupList.isEmpty()){
                Constants.arrayGropus=mGroupList;
                Log.v("GroupCheck",mGroupList.size()+" ");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
