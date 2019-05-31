package com.example.dropboxtest.Objects;

import java.util.ArrayList;

public class Post {
    private String id;
    private String postContext;
    private String postPublisher;
    private int numberOfComments;
    private String time;
    private String type;
    private ArrayList<Comment> comments=new ArrayList<>();
    private String postPath;

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
    public void addElement(String name,String commentText){
        Comment comment=new Comment();
        comment.setName(name);
        comment.setCommentText(commentText);
        comments.add(comment);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public ArrayList<Comment> getComments(){
        return comments;
    }

    public String getPostPath() {
        return postPath;
    }

    public void setPostPath(String postPath) {
        this.postPath = postPath;
    }
}
