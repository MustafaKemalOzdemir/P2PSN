package com.example.dropboxtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;

public class MessageActivity extends AppCompatActivity {
    private ApplicationProvider applicationProvider=new ApplicationProvider(this);
    static MessageAdapter messageAdapter;
    static RecyclerView recyclerView;
    static ArrayList<MessageSample> messageSampleArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        int index=intent.getExtras().getInt("receiver");
        final Friend friend=Constants.arrayFriends.get(index);
        final EditText editText=findViewById(R.id.sendMessageEditText);
        applicationProvider.updateMessages(friend.getFolderPath()+"/messages.txt",messageSampleArrayList);






        recyclerView=findViewById(R.id.messageRecycler);
        messageAdapter=new MessageAdapter(messageSampleArrayList);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        FloatingActionButton sendButton = findViewById(R.id.fab);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=editText.getText().toString();
                String time=Calendar.getInstance().getTime().getTime()+"";
                applicationProvider.sendMessage(friend.getFolderPath()+"/messages.txt",message,Constants.User,time,messageSampleArrayList);

                editText.getText().clear();

            }
        });
    }
    public static void notifyAdapter(){

        messageAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(messageSampleArrayList.size()-1);

    }

}
