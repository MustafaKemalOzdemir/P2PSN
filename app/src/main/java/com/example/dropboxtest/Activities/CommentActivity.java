package com.example.dropboxtest.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.dropboxtest.ApplicationProvider;
import com.example.dropboxtest.CommentAdapter;
import com.example.dropboxtest.Objects.Comment;
import com.example.dropboxtest.Objects.Post;
import com.example.dropboxtest.R;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {
    static CommentAdapter commentAdapter;
    ApplicationProvider applicationProvider = new ApplicationProvider(this);
    ArrayList<Comment> currentComments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        ArrayList<String> name;
        ArrayList<String> comments;
        Post post = new Post();

        if (extras != null) {

            name = extras.getStringArrayList("postNames");
            comments = extras.getStringArrayList("postComments");
            for (int i = 0; i < name.size(); i++) {
                post.addElement(name.get(i), comments.get(i));
            }
            if(currentComments.isEmpty()){
                currentComments=post.getComments();
            }
            post.setPostPath(extras.getString("postPath"));
            post.setId(extras.getString("id"));

            final Post dummyPost=post;


            RecyclerView recyclerView = findViewById(R.id.commentRecycler);
            commentAdapter = new CommentAdapter(currentComments);
            recyclerView.setAdapter(commentAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ImageButton imageButton = findViewById(R.id.commentAddButton);
            final EditText editText = findViewById(R.id.commentEditText);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = editText.getText().toString();
                    editText.setText("");
                    applicationProvider.addComment(text, dummyPost);
                    applicationProvider.updateComments(dummyPost.getId(),dummyPost.getPostPath(),currentComments);

                }
            });


        }

    }
    public static void notifyAdapter(){
        commentAdapter.notifyDataSetChanged();
    }
}
