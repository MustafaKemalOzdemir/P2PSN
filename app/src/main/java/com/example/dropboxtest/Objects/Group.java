package com.example.dropboxtest.Objects;

import java.util.ArrayList;

public class Group {
    private String groupName;
    //private String folderId;
    private String folderPath;
    public ArrayList<Post> posts=new ArrayList<>();

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    //public String getFolderId() {
    //    return folderId;
    //}

    //public void setFolderId(String folderId) {
    //    this.folderId = folderId;
    //}

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
