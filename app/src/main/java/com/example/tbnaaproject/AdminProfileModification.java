package com.example.tbnaaproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminProfileModification extends AppCompatActivity {

    EditText Name, username, email, password, phone, city;
    Button apply, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile_modification);

        Name=(EditText)findViewById(R.id.admin_textEdit_Name);
        username=(EditText)findViewById(R.id.admin_textEdit_username);
        email=(EditText)findViewById(R.id.admin_textEdit_Email);
        password=(EditText)findViewById(R.id.admin_textEdit_Password);




    }
}