package com.example.dropboxtest.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dropboxtest.ApplicationProvider;
import com.example.dropboxtest.AsyncAddFriend;
import com.example.dropboxtest.Dropbox2Provider;
import com.example.dropboxtest.Friend;
import com.example.dropboxtest.R;

public class FragmentNotifications extends Fragment {
    static Friend friend=new Friend();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notifications, container, false);


        final ApplicationProvider applicationProvider=new ApplicationProvider(v.getContext());
        final Dropbox2Provider dropbox2Provider=new Dropbox2Provider(v.getContext());

        final EditText editText=v.findViewById(R.id.editText);
        Button ListFolders=v.findViewById(R.id.buttonListFolders);
        ListFolders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText.getText().toString();
                dropbox2Provider.addFolderMember("5457367296","kemal.ozdemir@agu.edu.tr");
            }
        });
        Button AddFriend=v.findViewById(R.id.buttonAddFriend);
        AddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText.getText().toString();
                applicationProvider.addFriend(email);
            }
        });
        Button CreateFolder=v.findViewById(R.id.buttonCreateFolder);
        CreateFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText.getText().toString();
                applicationProvider.addFriend2(email,friend);
            }
        });
        Button UploadString=v.findViewById(R.id.buttonUploadString);
        UploadString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText.getText().toString();
                Log.v("TestV2",email);
            }
        });
        Button ShareFolder=v.findViewById(R.id.buttonShareFolder);
        ShareFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path=editText.getText().toString();
                dropbox2Provider.shareFolder(path);
            }
        });
        return v;
    }
}
