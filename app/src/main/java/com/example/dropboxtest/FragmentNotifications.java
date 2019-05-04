package com.example.dropboxtest;

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

public class FragmentNotifications extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notifications, container, false);

        Dropbox2Provider dropbox2Provider=new Dropbox2Provider(v.getContext());

        final EditText editText=v.findViewById(R.id.editText);
        Button ListFolders=v.findViewById(R.id.buttonListFolders);
        ListFolders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText.getText().toString();
                Log.v("TestV2",email);
            }
        });
        Button AddFriend=v.findViewById(R.id.buttonAddFriend);
        AddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText.getText().toString();
                Log.v("TestV2",email);
            }
        });
        Button CreateFolder=v.findViewById(R.id.buttonCreateFolder);
        CreateFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText.getText().toString();
                Log.v("TestV2",email);
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
                String email=editText.getText().toString();
                Log.v("TestV2",email);
            }
        });
        return v;
    }
}
