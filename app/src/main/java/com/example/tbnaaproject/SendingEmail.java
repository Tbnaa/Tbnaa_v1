package com.example.tbnaaproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static java.util.Objects.requireNonNull;

public class SendingEmail extends AppCompatActivity {
    EditText messageText, subject;
    Button sendButton;
//    String senderEmail, senderPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_email);

        messageText = (EditText) findViewById(R.id.message_xml);
        subject = (EditText) findViewById(R.id.subject_xml);
        sendButton = (Button) findViewById(R.id.sendButton_xml);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendMail();
            }
        });
    }

            private void sendMail() {

                String messageString = messageText.getText().toString();
                String subjectString = subject.getText().toString().trim();
                String toEmail = "duta.1779@gmail.com";

                //send Mail
                JavaMailAPI javaMailAPI = new JavaMailAPI(this, messageString, subjectString, toEmail);

                //execute
                javaMailAPI.execute();
            }

           }

