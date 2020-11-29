package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.mail.internet.InternetAddress;

public class About extends AppCompatActivity {

    ImageView emailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        emailImage = (ImageView) findViewById(R.id.contact_us_email);

        emailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendEmail();
            }
        });

    }

    public void sendEmail() {

        Intent mail = new Intent();
        mail.setAction(Intent.ACTION_SEND);
        mail.setType("application/octet-stream");
        mail.putExtra(Intent.EXTRA_EMAIL, new String[]{"tbnaatest@gmail.com"});
        startActivity(mail);

    }
}