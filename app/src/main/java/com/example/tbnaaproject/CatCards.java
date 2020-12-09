package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tbnaaproject.adapters.CatCardsAdapter;
import com.example.tbnaaproject.R;
import com.example.tbnaaproject.models.Cats;

import java.util.ArrayList;

public class CatCards extends AppCompatActivity {

    GridView gridView;
    ArrayList<Cats> catsList;
    CatCardsAdapter catAdapter;
    TbnaaDatabase databaseHelper;
    Spinner sp1;
    Spinner sp2;

    String cities[]={"City","Riyadh","Abha","Dammam","Jeddah","Medina","Mecca"};
    String gender[]={"Gender","Female","Male"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_cards);

        gridView = (GridView) findViewById(R.id.catsGridView);

        databaseHelper = new TbnaaDatabase(CatCards.this);
        catsList = new ArrayList<Cats>();

        catsList = databaseHelper.getAllCats();
        catAdapter = new CatCardsAdapter(CatCards.this, catsList);
        gridView.setAdapter(catAdapter);

        //card selection event
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Open cat profile page
                Cats theCat = new Cats();
                Intent intent = new Intent(CatCards.this, CatProfile.class);
                intent.putExtra("catID", theCat.getCatId());
                startActivity(intent);
            }
        });

        //city spinner selection event
        sp1 = findViewById(R.id.sp1);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long itemID) {

                if(position == 0){
                    catsList = new ArrayList<Cats>();
                    catsList = databaseHelper.getAllCats();
                    catAdapter = new CatCardsAdapter(CatCards.this, catsList);
                    gridView.setAdapter(catAdapter);

                } else if (position > 0 && position < cities.length) {

                    String text = parent.getItemAtPosition(position).toString();
                    ArrayList<Cats> catssList = new ArrayList<Cats>();
                    ArrayList<Cats> allData = databaseHelper.getAllCats();

                    //filter by city
                    for (Cats catt : allData) {
                        if (catt.getCatCity().trim().equalsIgnoreCase(text.trim())) {
                            catssList.add(catt);
                        }
                    }

                    //instatiate adapter a
                    catAdapter = new CatCardsAdapter(CatCards.this, catssList);
                    //set the adapter to GridView
                    gridView.setAdapter(catAdapter);
                } else {
                    Toast.makeText(CatCards.this, "There are no data based on your selection.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        //Gender spinner selection event
        sp2 = findViewById(R.id.sp2);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long itemID) {
                if(position == 0){
                    catsList = new ArrayList<Cats>();
                    catsList = databaseHelper.getAllCats();
                    catAdapter = new CatCardsAdapter(CatCards.this, catsList);
                    gridView.setAdapter(catAdapter);

                } else if (position > 0 && position < gender.length) {

                    String text = parent.getItemAtPosition(position).toString();
                    ArrayList<Cats> catssList = new ArrayList<Cats>();
                    ArrayList<Cats> allData = databaseHelper.getAllCats();

                    //filter by city
                    for (Cats catt : allData) {
                        if (catt.getCatGender().trim().equalsIgnoreCase(text.trim())) {
                            catssList.add(catt);
                        }
                    }

                    //instatiate adapter a
                    catAdapter = new CatCardsAdapter(CatCards.this, catssList);
                    //set the adapter to GridView
                    gridView.setAdapter(catAdapter);
                } else {
                    Toast.makeText(CatCards.this, "There are no data based on your selection.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

    }
}