package com.example.tbnaaproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;

public class SignUp extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText signup_firstName_editText;
    private EditText signup_lastName_editText;
    private EditText signup_userName_editText;
    private EditText signup_email_editText;
    private EditText signup_password_editText;
    private EditText signup_phoneNo_editText;
    private EditText signup_DoB_editText;
    private Spinner signup_city_spinner;
    private Button signup_DoB_button;
    private Button getSignup_submit_button;
    private TbnaaDatabase databaseHelper;
    private RadioGroup genderRadioGroup, socialStateRadioGroup;
    private RadioButton userGenderRadioButton, socialStateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup_firstName_editText = (EditText)findViewById(R.id.signup_firstName_editText1);
        signup_lastName_editText = (EditText)findViewById(R.id.signup_lastName_editText1);
        signup_userName_editText = (EditText)findViewById(R.id.signup_userName_editText1);
        signup_email_editText = (EditText)findViewById(R.id.signup_email_editText1);
        signup_password_editText = (EditText)findViewById(R.id.signup_password_editText1);
        signup_phoneNo_editText = (EditText)findViewById(R.id.signup_phoneNo_editText1);
        signup_city_spinner = (Spinner)findViewById(R.id.signup_city_spinner1);
        signup_DoB_editText = (EditText)findViewById(R.id.signup_DoB_editText1);
        signup_DoB_button = (Button)findViewById(R.id.signup_DoB_button1);
        getSignup_submit_button = (Button)findViewById(R.id.signup_submit_button1);

        getSignup_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        //City Spinner
        final ArrayList<String> cityArrayList = new ArrayList<>();
        cityArrayList.add("Choose your city");
        cityArrayList.add("Khoubar");
        cityArrayList.add("Dammam");
        cityArrayList.add("Al-Bahah");
        cityArrayList.add("Jeddah");
        cityArrayList.add("Ryadh");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cityArrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        signup_city_spinner.setAdapter(arrayAdapter);
        signup_city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            //@RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                signup_city_spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        //DoB Calender
        signup_DoB_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

    }

    public  void register(){
        if(!validate()){
            Toast.makeText(getApplicationContext(),"Sign up Failed!",Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(getApplicationContext(),"Valid Info.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), CatCards.class);
            startActivity(intent);
        }
    }

    public  boolean validate(){
        String fName = signup_firstName_editText.getText().toString();
        String lName = signup_lastName_editText.getText().toString();
        String username = signup_userName_editText.getText().toString();
        String Email = signup_email_editText.getText().toString();
        String Password = signup_password_editText.getText().toString();
        String phoneNo = signup_phoneNo_editText.getText().toString();
        String DoB = signup_DoB_editText.getText().toString();

        boolean valid = true;

        if(fName.isEmpty()){
            signup_firstName_editText.setError("Enter your name");
            valid = false;
        }

        if(lName.isEmpty()){
            signup_lastName_editText.setError("Enter your name");
            valid = false;
        }

        if(username.isEmpty() || username.length() < 6){
            signup_userName_editText.setError("Enter a username greater than 6 char");
            valid = false;
        }

        if(Password.isEmpty() || Password.length() < 6){
            signup_password_editText.setError("Enter a Password greater than 6 char");
            valid = false;
        }

        if(Email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            signup_email_editText.setError("Please enter valid email address");
            valid = false;
        }

        if(phoneNo.isEmpty() || phoneNo.length() < 10){
            signup_phoneNo_editText.setError("Please enter valid phone No.");
            valid = false;
        }

        if(DoB.isEmpty()){
            signup_DoB_editText.setError("Enter a DOB");
            valid = false;
        }

        databaseHelper.addUser(null, Password, fName, lName, null, null,
                Email, phoneNo, null);

        return valid;
    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + (month+1) + "/" + year;
        signup_DoB_editText.setText(date);
    }
}