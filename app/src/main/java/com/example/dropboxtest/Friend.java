package com.example.dropboxtest;

public class Friend {
    private String Name;
    private String eMail;
    private String folderPath;
    private String folderId=null;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String filePath) {
        this.folderPath = filePath;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String fileId) {
        this.folderId = fileId;
    }
}
