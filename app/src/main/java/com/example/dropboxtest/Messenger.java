package com.example.dropboxtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;

public class Messenger extends AppCompatActivity implements OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messanger);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<Friend> friends=Constants.arrayFriends;


        MessengerRVAdapter adapter=new MessengerRVAdapter(this,friends,this);
        RecyclerView recyclerView=findViewById(R.id.messengerRecycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, Constants.arrayFriends.size()+"", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search,menu);
        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.v("rvtest",Constants.arrayFriends.get(clickedItemIndex).getName());
        Intent intent=new Intent(Messenger.this,MessageActivity.class);
        intent.putExtra("receiver",clickedItemIndex);
        startActivity(intent);

    }
}
