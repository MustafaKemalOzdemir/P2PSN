package com.example.dropboxtest.AsyncTasks;

import android.os.AsyncTask;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.sharing.AccessLevel;
import com.dropbox.core.v2.sharing.AddMember;
import com.dropbox.core.v2.sharing.MemberSelector;
import com.dropbox.core.v2.sharing.ShareFolderLaunch;
import com.dropbox.core.v2.sharing.SharedFolderMetadata;
import com.example.dropboxtest.Constants;
import com.example.dropboxtest.Friend;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AsyncCreateGroup extends AsyncTask<Void,Void,Void> {
    DbxClientV2 client;
    String groupName;
    ArrayList<Friend> friendArrayList;
    public AsyncCreateGroup(DbxClientV2 client,String groupName,ArrayList<Friend> friendArrayList){
        this.client=client;
        this.groupName=groupName;
        this.friendArrayList=friendArrayList;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        String groupPath=Constants.Group_Folder_path+"/"+groupName;
        String folderId;
        try {
            //create and share folder
            ShareFolderLaunch shareFolderLaunch=client.sharing().shareFolderBuilder(groupPath).start();
            SharedFolderMetadata sharedFolderMetadata=shareFolderLaunch.getCompleteValue();
            folderId=sharedFolderMetadata.getSharedFolderId();

            //upload Group.txt

            InputStream in=new ByteArrayInputStream(Constants.getGroupTemplate().getBytes());
            client.files().uploadBuilder(groupPath+"/Group.txt").uploadAndFinish(in);
            

            //add folder members to group
            List<AddMember> members=new ArrayList<>();
            for(int i=0;i<friendArrayList.size();i++){
                AddMember member= new AddMember(MemberSelector.email(friendArrayList.get(i).geteMail()),AccessLevel.EDITOR);
                members.add(member);
            }
            client.sharing().addFolderMember(folderId,members);

        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
