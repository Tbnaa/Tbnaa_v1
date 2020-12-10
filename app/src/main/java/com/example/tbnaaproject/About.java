
package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import javax.mail.internet.InternetAddress;

public class About extends AppCompatActivity {

    ImageView emailImage;
    ImageView webImage;
    ImageView tweetImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        emailImage = (ImageView) findViewById(R.id.contact_us_email);
        webImage = (ImageView) findViewById(R.id.websiteImage);
        tweetImage = (ImageView) findViewById(R.id.tweetImage);

        emailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

        webImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebsite();
            }
        });

        tweetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentwitter();
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

    public void openWebsite() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://tbnaa.com"));
        startActivity(browserIntent);
    }

    public void opentwitter() {
        Intent twitter = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/TbnaaCats?s=20"));
        startActivity(twitter);
    }
}