package com.example.dropboxtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ButtomNavigation_Activity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment SelectedFragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    SelectedFragment=new FragmentHome();
                    break;

                case R.id.navigation_dashboard:
                    SelectedFragment=new FragmentAddFriend();
                    break;

                case R.id.navigation_notifications:
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,SelectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttom_navigation_);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentHome()).commit();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Fragment not created!!!!!


    }

}
