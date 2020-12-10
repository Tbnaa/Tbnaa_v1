package com.example.tbnaaproject.models;


import android.graphics.Bitmap;

import com.google.gson.Gson;

import java.io.Serializable;

public class Cats implements Serializable {

    private int catId;
    private byte[] catImage;
    private String catName = "";
    private String catCity = "";
    private String catGender = "";



    // Setters

    public void setID(int catId) { this.catId = catId;}

    public void setImage(byte[] catImage) { this.catImage = catImage;}

    public void setName(String catName) { this.catName = catName;}

    public void setCity(String catCity) { this.catCity = catCity;}

    public void setGender(String catGender) { this.catGender = catGender;}

    // Getters

    public int getCatId() {
        return this.catId;
    }

    public byte[] getCatImage() { return this.catImage;}

    public String getCatName() {
        return this.catName;
    }

    public String getCatCity() {
        return this.catCity;
    }

    public String getCatGender() {
        return this.catGender;
    }


    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Cats fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Cats.class);
    }


}
