package com.example.tbnaaproject;

public class TbnaaMemberWords {

    private String Name;
    private String Phone;
    private String Email;
    private int mimageResourceId = -1;


    public TbnaaMemberWords(String Name, String Phone, String Email, int imageResourceId) {
        Name = Name;
        Phone = Phone;
        Email = Email;
        mimageResourceId = imageResourceId;
    }

    public String getName() {
        return Name;
    }
    public String getEmail() {
        return Email;
    }
    public String getPhone() {
        return Phone;
    }

    public int getmImageResourceId() {
        return mimageResourceId;
    }

}

