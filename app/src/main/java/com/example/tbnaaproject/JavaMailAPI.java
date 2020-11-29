package com.example.tbnaaproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailAPI extends AsyncTask<Void,Void,Void> {

    String mailMessage, subject, email;

    private Context context;
    private Session  mailSession ;

    public static final String senderEmail="tbnaatest@gmail.com";
    public static final String senderPassword="mm_12341234";
    public static final String toEmail = "duta.1779@gmail.com";

    // Initialize progress dialog
    private ProgressDialog progressDialog;

    //Constructor
    public  JavaMailAPI(Context context,String mailMessage, String subject, String email){
        this.context=context;
        this.mailMessage=mailMessage;
        this.subject=subject;
        this.email=email;
    }

    //-----------------------------------------
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // show progress
        progressDialog=ProgressDialog.show(context,
                "Please Wait", "Sending Mail ...",
                false , false );
    }
    //-----------------------------------------

    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);

        //dismiss progress dialog
        progressDialog.dismiss();
        //show success message
        Toast.makeText(context,"Message successfully sent",Toast.LENGTH_LONG).show();

    }
    //---------------------------------------

    @Override
    protected Void doInBackground(Void... voids) {
        //Initialize properties
        Properties properties= new Properties();

        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");

        //Initialize session
        mailSession=Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            //Authentication
            protected PasswordAuthentication getPasswordAuthentication(){
                //Sender email & password
                return new  PasswordAuthentication(senderEmail,senderPassword);
            }
        });

        try {

            //Initialize Email Message
            MimeMessage message = new MimeMessage(mailSession);

            //sender email
            message.setFrom(new InternetAddress(senderEmail));

            // Recipient email
            message.addRecipients(javax.mail.Message.RecipientType.TO,
                    String.valueOf(new InternetAddress(toEmail)));

            //Email Subject
            message.setSubject(subject);

            //Email Message
            message.setText(mailMessage);

            //sending Email
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;

    }
}
