package com.example.dropboxtest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    public Context context=this;
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences=this.getSharedPreferences("DropBox",MODE_PRIVATE);
        String token=sharedPreferences.getString("DropBox_token","");
        if(!token.equals("")){
            Constants.ACCESS_TOKEN=token;
        } else{

            Toast.makeText(getApplicationContext(),"You must sign up",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        Button LogIn=findViewById(R.id.button_login);
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Constants.ACCESS_TOKEN!=""){
                    //DropboxProvider dropboxProvider=new DropboxProvider(context);

                    Intent intent=new Intent(LogInActivity.this,ButtomNavigation_Activity.class);
                    startActivity(intent);

                }

            }
        });

        Button SignIn=(Button)findViewById(R.id.button_signin);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogInActivity.this,Cloud.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
