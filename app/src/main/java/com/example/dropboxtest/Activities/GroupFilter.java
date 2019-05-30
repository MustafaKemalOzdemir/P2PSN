package com.example.dropboxtest.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dropboxtest.Constants;
import com.example.dropboxtest.GroupFilterAdapter;
import com.example.dropboxtest.Objects.Friend;
import com.example.dropboxtest.Objects.Group;
import com.example.dropboxtest.OnItemClickListener;
import com.example.dropboxtest.R;

import java.util.ArrayList;

public class GroupFilter extends AppCompatActivity implements OnItemClickListener,SearchView.OnQueryTextListener {
    ArrayList<Group> groups=Constants.arrayGropus;
    ArrayList<Group> clickedGroups=new ArrayList<>();
    GroupFilterAdapter groupFilterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.v("GroupCheck",groups.size()+"s");

        RecyclerView recyclerView=findViewById(R.id.groupFilterRecycler);
        groupFilterAdapter=new GroupFilterAdapter(groups,clickedGroups,this);
        recyclerView.setAdapter(groupFilterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem menuItem=menu.findItem(R.id.searchButton);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String input=s.toLowerCase();
        ArrayList<Group> newList=new ArrayList<>();
        for (Group group:groups) {
            if(group.getGroupName().toLowerCase().contains(input)){
                newList.add(group);
            }
        }
        groupFilterAdapter.updateList(newList);
        return true;
    }

}
