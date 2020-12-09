package com.example.tbnaaproject;

public class AddingAdminWord {
    private String catName;
    private String userName;
    private int mImageResourceId = -1;

    public AddingAdminWord(String CatName, String UserName, int imageResourceId) {
        catName = CatName;
        userName = UserName;
        mImageResourceId = imageResourceId;
    }

    public String getCatName() {
        return catName;
    }

    public String getUserName() {
        return userName;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }
}
