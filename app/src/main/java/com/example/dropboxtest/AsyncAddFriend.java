package com.example.dropboxtest;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DownloadErrorException;
import com.dropbox.core.v2.sharing.AccessLevel;
import com.dropbox.core.v2.sharing.AddFileMemberErrorException;
import com.dropbox.core.v2.sharing.AddMember;
import com.dropbox.core.v2.sharing.MemberSelector;
import com.dropbox.core.v2.sharing.ShareFolderErrorException;
import com.dropbox.core.v2.sharing.UserInfo;
import com.dropbox.core.v2.sharing.UserMembershipInfo;
import com.example.dropboxtest.AsyncTasks.AsyncAddFolderMember;
import com.example.dropboxtest.AsyncTasks.AsyncShareFolder;
import com.example.dropboxtest.AsyncTasks.AsyncUploadString;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AsyncAddFriend extends AsyncTask<Void,String,String> {
    DbxClientV2 client;
    String path;
    Context context;
    String eMail;
    Friend newFriend;

    public AsyncAddFriend(DbxClientV2 client,Context context,String path,String eMail){
        this.client=client;
        this.context=context;
        this.path=path;
        this.eMail=eMail;
    }
    @Override
    protected String doInBackground(Void... voids) {
        Log.v("addFriend","Started AsyncAddFriend");
        String response;
        String folderId="";
        ArrayList<Friend> mFriends=new ArrayList<>();
        Boolean ListChecker=false;


        StringBuilder builderResult=null;
        //Download Personal/Friends.txt for check

        try {
            InputStream in;
            in=client.files().downloadBuilder(Constants.Personal_Friends_Folder_path).start().getInputStream();
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

        try {
            JSONObject jsonObject=new JSONObject(builderResult.toString());
            JSONArray friendList=jsonObject.getJSONArray("friends");

                Friend friend;
                if(Constants.arrayFriends.size()!=friendList.length()){
                    ListChecker=true;
                    for(int i=0;i<friendList.length();i++){
                        friend=new Friend();
                        JSONObject friendElement=friendList.getJSONObject(i);
                        friend.seteMail(friendElement.get("E-Mail"));
                        friend.setFolderId(friendElement.get("Folder-Id"));
                        friend.setFolderPath(friendElement.get("Folder-Path"));
                        friend.setName(friendElement.get("Name"));
                        friend.setAccountId(friendElement.getString("Account-Id"));
                        //friend.setPpPath(friendElement.get("Pp-Path"));

                    }

                }



        } catch (JSONException e) {
            Log.v("DownloadPhase",e.getMessage());
        }


        //Download and parsing completed


        try {
            response=client.sharing().shareFolderBuilder(path).start().toString();
            JSONObject jsonObject=new JSONObject(response);
            folderId=jsonObject.getString("shared_folder_id");
            newFriend.setFolderId(folderId);

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

            InputStream in=new ByteArrayInputStream(Constants.GetFriendTemplate().getBytes());
            client.files().uploadBuilder(path+"/messages.txt").uploadAndFinish(in).toString();
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        //get user details
        try {
            String currentEmail=client.users().getCurrentAccount().getEmail();
            List<UserMembershipInfo> users=client.sharing().listFolderMembers(folderId).getUsers();
            for(int k=0;k<users.size();k++){
                UserInfo user=users.get(k).getUser();
                newFriend.setAccountId(user.getAccountId());
                newFriend.setName(user.getDisplayName());
                newFriend.seteMail(user.getEmail());
                newFriend.setFolderPath(Constants.Friends_Folder_Path+currentEmail+"_"+user.getEmail());
                //think
                //problems problems
                mFriends.add(newFriend);
            }
        } catch (DbxException e) {
            e.printStackTrace();
        }
        if(ListChecker){
            //full upload

        }else{
            //partial upload

            try {
                JSONObject jsonObject=new JSONObject(builderResult.toString());
                JSONArray jsonArray=jsonObject.getJSONArray("friends");
                JSONObject objectNewFriend=new JSONObject();
                objectNewFriend.put("Name",newFriend.getName());
                objectNewFriend.put("E-Mail",newFriend.getName());
                objectNewFriend.put("Folder-Id",newFriend.getFolderId());
                objectNewFriend.put("Account-Id",newFriend.getAccountId());
                objectNewFriend.put("Folder-Path",newFriend.getFolderPath());

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }




        return null;
    }
}
