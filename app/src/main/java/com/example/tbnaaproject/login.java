package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private EditText login_email_editText;
    private EditText login_password_editText;
    private TextView login_signupLink_hyperText;
    private Button login_submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email_editText = (EditText)findViewById(R.id.login_email_editText);
        login_password_editText = (EditText)findViewById(R.id.login_password_editText);
        login_signupLink_hyperText = (TextView)findViewById(R.id.login_signupLink_hyperText);
        login_submit_button = (Button) findViewById(R.id.login_submit_button);

        login_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    public  void register(){
        String Email = login_email_editText.getText().toString();
        String Password = login_password_editText.getText().toString();

        if(!validate()){
            Toast.makeText(getApplicationContext(),"Login Failed!",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),"Valid Info.",Toast.LENGTH_LONG).show();
        }
    }

    public  boolean validate(){
        String Email = login_email_editText.getText().toString();
        String Password = login_password_editText.getText().toString();

        boolean valid = true;

        if(Password.isEmpty() || Password.length() < 6){
            login_password_editText.setError("Your Password is less than 6 Char");
            valid = false;
        }

        if(Email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            login_email_editText.setError("Please enter valid email address");
            valid = false;
        }

        return valid;
    }
}