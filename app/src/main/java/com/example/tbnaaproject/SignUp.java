package com.example.tbnaaproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class SignUp extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    //TODO: Verification of form info

    TextView signup_DoB_editText;
    Button signup_DoB_button;
    Spinner signup_city_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup_city_spinner = findViewById(R.id.signup_city_spinner);
        ArrayList<String> cityArrayList = new ArrayList<>();
        cityArrayList.add("Khoubar");
        cityArrayList.add("Dammam");
        cityArrayList.add("Jeddah");
        cityArrayList.add("Ryadh");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cityArrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        signup_city_spinner.setAdapter(arrayAdapter);
        signup_city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                signup_city_spinner.setTooltipText(tutorialsName);
                //TODO: Set city name
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        signup_DoB_editText = findViewById(R.id.signup_DoB_editText);
        signup_DoB_button = findViewById(R.id.signup_DoB_button);

        findViewById(R.id.signup_DoB_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
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
        String date = dayOfMonth + "/" + month + "/" + year;
        signup_DoB_editText.setText(date);
    }
}