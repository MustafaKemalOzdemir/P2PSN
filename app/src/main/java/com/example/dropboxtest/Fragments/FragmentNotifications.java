package com.example.dropboxtest.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dropboxtest.Activities.GroupFilter;
import com.example.dropboxtest.ApplicationProvider;
import com.example.dropboxtest.R;

public class FragmentNotifications extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notifications, container, false);
        getActivity().setTitle("Notifications");

        ApplicationProvider applicationProvider=new ApplicationProvider(v.getContext());
        applicationProvider.updateGroups();


        final EditText editText=v.findViewById(R.id.editText);
        Button CreateFolder=v.findViewById(R.id.buttonCreateFolder);
        CreateFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText.getText().toString();
                Intent intent=new Intent(v.getContext(),GroupFilter.class);
                intent.putExtra("WhereFrom","share post");
                intent.putExtra("postContext",email);
                startActivity(intent);

            }
        });

        return v;
    }
}
