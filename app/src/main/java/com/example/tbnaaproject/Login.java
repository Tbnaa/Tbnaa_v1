package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText login_email_editText;
    private EditText login_password_editText;
    private TextView login_signupLink_hyperText;
    private Button login_submit_button;
    private boolean valid = true;
    private TbnaaDatabase databaseHelper;
    private static final int REQUEST_SIGNUP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email_editText = (EditText) findViewById(R.id.login_email_editText);
        login_password_editText = (EditText) findViewById(R.id.login_password_editText);
        login_submit_button = (Button) findViewById(R.id.login_submit_button);
        login_signupLink_hyperText = (TextView) findViewById(R.id.login_signupLink_hyperText);

        databaseHelper = new TbnaaDatabase(Login.this);

        for (int i = 0; i >= 6; i++){
            databaseHelper.addAdmin(R.drawable.user, "password",
                    "Maryam", "Jamal", "female",
                    "mema.blue.mo@gmail.com", "0552223333");
        }

        login_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(valid);
            }
        });

        login_signupLink_hyperText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login(boolean valid) {
        String Email = login_email_editText.getText().toString();
        String Password = login_password_editText.getText().toString();

        if (Password.isEmpty() || Password.length() < 6) {
            valid = false;
        }

        if (Email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
        }

        if (valid) {
            Intent intent = new Intent(getApplicationContext(), CatCards.class);
            startActivity(intent);
        } else {
            login_email_editText.setError("Enter valid email address");
            login_password_editText.setError("check your Password is valid and less than 6 Char");
        }
    }
}