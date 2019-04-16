package com.example.dropboxtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dropbox.core.android.Auth;

public class Cloud extends AppCompatActivity {
    public Context Context =this;
    private boolean DropboxSelected=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button dropbox=(Button)findViewById(R.id.button_selectdropbox);
        dropbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DropboxSelected=true;
                CloudDropbox cloudDropbox=new CloudDropbox(Context);
                cloudDropbox.DropboxAuthentication();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences=getPreferences(MODE_PRIVATE);
                String getToken=sharedPreferences.getString("DropBox_token","");
                Log.v("DTest","GelenToken"+getToken);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String token;
        if(DropboxSelected){
            //Get token from dropBox.
            token= Auth.getOAuth2Token();

            if(token!=null){
                //Saving token
                SharedPreferences sharedPreferences=this.getSharedPreferences("DropBox",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("DropBox_token",token);
                editor.apply();

                Log.v("DToken",token);
            }
        }


    }
}
