package com.example.dropboxtest;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.sharing.AccessLevel;
import com.dropbox.core.v2.sharing.AddFileMemberErrorException;
import com.dropbox.core.v2.sharing.AddMember;
import com.dropbox.core.v2.sharing.MemberSelector;
import com.dropbox.core.v2.sharing.ShareFolderErrorException;
import com.example.dropboxtest.AsyncTasks.AsyncAddFolderMember;
import com.example.dropboxtest.AsyncTasks.AsyncShareFolder;
import com.example.dropboxtest.AsyncTasks.AsyncUploadString;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AsyncAddFriend extends AsyncTask<Void,String,String> {
    DbxClientV2 client;
    String path;
    Context context;
    String eMail;
    String string;
    Friend friend;
    public AsyncAddFriend(DbxClientV2 client,Context context,String path,String eMail,String string,Friend friend){
        this.client=client;
        this.context=context;
        this.path=path;
        this.eMail=eMail;
        this.string=string;
        this.friend=friend;
    }
    @Override
    protected String doInBackground(Void... voids) {
        Log.v("addFriend","Started AsyncAddFriend");
        String response;
        String folderId="";
        try {
            response=client.sharing().shareFolderBuilder(path).start().toString();
            JSONObject jsonObject=new JSONObject(response);
            folderId=jsonObject.getString("shared_folder_id");

        }
        catch (ShareFolderErrorException error){
            if( error.errorValue.isBadPath()){
                Log.v("TaskTest52",error.errorValue.toString());
                response="badPath";

            }

        } catch (DbxException e) {
            e.printStackTrace();
            response="error";
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            List<AddMember> array=new ArrayList<>();
            AddMember member= new AddMember(MemberSelector.email(eMail),AccessLevel.EDITOR);
            array.add(member);
            Log.v("folderID","folderid="+folderId);
            client.sharing().addFolderMember(folderId,array);


        } catch (AddFileMemberErrorException e){
            //e.errorValue.


        } catch (DbxException e) {
            e.printStackTrace();
            Log.v("addFriend",e.getMessage());

        }
        try {
            InputStream in=new ByteArrayInputStream(string.getBytes());
            string=client.files().uploadBuilder(path+"/naber.txt").uploadAndFinish(in).toString();
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }





        return null;
    }
}
