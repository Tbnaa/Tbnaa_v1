package com.example.tbnaaproject;

import android.view.View;
import android.widget.ImageButton;

import java.io.Serializable;

public class AddingAdminWord implements Serializable {


    private String userName;
    private byte[] catImage;
    private String catName = "";
    private int catID;
    private ImageButton yes, no;


    public void setImage(byte[] catImage) { this.catImage = catImage;}

    public void setName(String catName) { this.catName = catName;}

    public void setUserName() { this.userName= userName; }

    public void setCatID() { this.catID= catID; }

    public int getCatID() {return this.catID;}

    public byte[] getCatImage() { return this.catImage;}

    public String getCatName() {
        return this.catName;
    }

    public String getUserName(){return this.userName;}


}
