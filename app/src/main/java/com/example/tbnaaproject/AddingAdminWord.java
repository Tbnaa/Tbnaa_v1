package com.example.tbnaaproject;

import java.io.Serializable;

public class AddingAdminWord implements Serializable {

    private String userName;
    private byte[] catImage;
    private String catName = "";


    public void setImage(byte[] catImage) { this.catImage = catImage;}

    public void setName(String catName) { this.catName = catName;}

    public void setUserName() { this.userName= userName; }

    public byte[] getCatImage() { return this.catImage;}

    public String getCatName() {
        return this.catName;
    }

    public String getUserName(){return this.userName;}
}
