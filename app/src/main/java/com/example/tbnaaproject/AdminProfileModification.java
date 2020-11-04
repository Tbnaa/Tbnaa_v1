package com.example.tbnaaproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class AdminProfileModification extends AppCompatActivity {

    Spinner admin_city_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile_modification);

        admin_city_spinner = findViewById(R.id.admin_city_spinner);
        ArrayList<String> cityArrayList = new ArrayList<>();
        cityArrayList.add("Khoubar");
        cityArrayList.add("Dammam");
        cityArrayList.add("Jeddah");
        cityArrayList.add("Ryadh");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cityArrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        admin_city_spinner.setAdapter(arrayAdapter);
        admin_city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                admin_city_spinner.setTooltipText(tutorialsName);
                //TODO: Set city name
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });


    }
}