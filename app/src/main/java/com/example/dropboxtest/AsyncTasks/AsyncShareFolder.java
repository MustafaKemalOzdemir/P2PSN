package com.example.dropboxtest.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.UploadSessionCursor;
import com.dropbox.core.v2.sharing.AclUpdatePolicy;
import com.dropbox.core.v2.sharing.ShareFolderError;
import com.dropbox.core.v2.sharing.ShareFolderErrorException;
import com.example.dropboxtest.TaskCompleted;

public class AsyncShareFolder extends AsyncTask<Void, String,String> {
    DbxClientV2 client;
    String path;
    TaskCompleted taskCompleted;
    Context context;

    public AsyncShareFolder(DbxClientV2 client, String path, TaskCompleted taskCompleted, Context context){
        this.client=client;
        this.path=path;
        this.taskCompleted=taskCompleted;
        this.context=context;
    }
    @Override
    protected String doInBackground(Void... voids) {
            String response="";
            try {
                response=client.sharing().shareFolderBuilder(path).start().toString();


            }
            catch (ShareFolderErrorException error){
               if( error.errorValue.isBadPath()){
                   Log.v("TaskTest52",error.errorValue.toString());
                   response="badPath";

               }

            } catch (DbxException e) {
                e.printStackTrace();
                response="error";
            }
        Log.v("TaskTest5",response);

            return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s.equals("badPath")){
            Toast.makeText(context,"Bad Path",Toast.LENGTH_SHORT).show();
        }else if(s.equals("error")){
            Toast.makeText(context,"Unknown Error",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
        }
        taskCompleted.onTaskComplete(s);
    }
}
