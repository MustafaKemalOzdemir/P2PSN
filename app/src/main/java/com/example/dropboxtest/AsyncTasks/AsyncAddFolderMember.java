package com.example.dropboxtest.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.sharing.AccessLevel;
import com.dropbox.core.v2.sharing.AddFileMemberErrorException;
import com.dropbox.core.v2.sharing.AddMember;
import com.dropbox.core.v2.sharing.MemberSelector;
import com.example.dropboxtest.TaskCompleted;

import java.util.ArrayList;
import java.util.List;

public class AsyncAddFolderMember extends AsyncTask<Void,String,String> {

    private String folderId;
    private String eMail;
    private boolean autoRename;
    private DbxClientV2 client;
    public TaskCompleted taskCompleted;

    public AsyncAddFolderMember(DbxClientV2 client,String folderId,String eMail){
        this.client=client;
        this.folderId=folderId;
        this.eMail=eMail;

    }


    @Override
    protected String doInBackground(Void... voids) {
        try {
            List<AddMember> array=new ArrayList<>();
            AddMember member= new AddMember(MemberSelector.email(eMail),AccessLevel.EDITOR);
            array.add(member);
            client.sharing().addFolderMember(folderId,array);


        } catch (AddFileMemberErrorException e){
            //e.errorValue.


        } catch (DbxException e) {
            e.printStackTrace();
            Log.v("addFriend",e.getMessage());

        }

        return null;
    }
}
