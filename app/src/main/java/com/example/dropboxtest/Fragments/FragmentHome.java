package com.example.dropboxtest.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.dropboxtest.Activities.ButtomNavigation_Activity;
import com.example.dropboxtest.Activities.GroupFilter;
import com.example.dropboxtest.HomeAdapter;
import com.example.dropboxtest.OnItemClickListener;
import com.example.dropboxtest.R;

public class FragmentHome extends Fragment implements OnItemClickListener {
    private LinearLayout photos, camera;
    public static  HomeAdapter homeAdapter;
    LinearLayout linearLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Home");
        View v= inflater.inflate(R.layout.fragment_home,container,false);
        photos = (LinearLayout) v.findViewById(R.id.btnphotos);
        camera = (LinearLayout) v.findViewById(R.id.btncamera);
        linearLayout = (LinearLayout) v.findViewById(R.id.lin1);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(this,'h',Toast.LENGTH_LONG).show();
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });
        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                final int ACTIVITY_SELECT_IMAGE = 1234;
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
            }
        });
        FloatingActionButton floatingActionButton=v.findViewById(R.id.filtergroup);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),GroupFilter.class);
                intent.putExtra("WhereFrom","filter posts");
                startActivity(intent);

            }
        });
        RecyclerView recyclerView=v.findViewById(R.id.recyhome);
        homeAdapter=new HomeAdapter(ButtomNavigation_Activity.posts,getActivity(),this);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        return v;
    }
    public static void notifyAdapter(){
        homeAdapter.notifyDataSetChanged();
        Log.v("refreshPosts",ButtomNavigation_Activity.posts.size()+" =adapterPostSize");
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.v("CommentSection",clickedItemIndex+"");

    }
}

