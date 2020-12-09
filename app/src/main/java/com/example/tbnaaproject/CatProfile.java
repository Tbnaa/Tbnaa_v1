package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tbnaaproject.models.Cats;

public class CatProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_profile);

        Intent intent = getIntent();
        Cats catProf = (Cats) intent.getSerializableExtra("catID");
    }
}