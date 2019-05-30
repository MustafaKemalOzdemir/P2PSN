package com.example.dropboxtest.Objects;

import java.util.ArrayList;

public class Post {
    private String id;
    private String postContext;
    private String postPublisher;
    private int numberOfComments;
    private ArrayList<String> comments=new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostContext() {
        return postContext;
    }

    public void setPostContext(String postContext) {
        this.postContext = postContext;
    }

    public String getPostPublisher() {
        return postPublisher;
    }

    public void setPostPublisher(String postPublisher) {
        this.postPublisher = postPublisher;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }
}
