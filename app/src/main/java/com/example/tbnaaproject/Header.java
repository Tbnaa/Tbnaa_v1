package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class Header extends AppCompatActivity {

    public TextView pageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);

        pageName = (TextView)findViewById(R.id.pageName);
        //CatCards obj = new CatCards(pageName);

    }

}