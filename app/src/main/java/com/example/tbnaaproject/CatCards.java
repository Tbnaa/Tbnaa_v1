package com.example.tbnaaproject;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tbnaaproject.adapters.CatCardsAdapter;
import com.example.tbnaaproject.R;
import com.example.tbnaaproject.models.Cats;
import com.google.android.material.navigation.NavigationView;

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

    ImageView menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_cards);

//        mDrawerLayout = findViewById(R.id.drawer_layout);
//        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
//        menuIcon.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                if(!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
//                    mDrawerLayout.openDrawer(GravityCompat.START);
//                } else {
//                    mDrawerLayout.closeDrawer(GravityCompat.START);
//                }
//            }
//        });

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment bottomFragment = manager.findFragmentById(R.id.menusFragment);
        ft.hide(bottomFragment);
        ft.commit();

        menuIcon = (ImageView) findViewById(R.id.menuIcon);
        menuIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                Fragment bottomFragment = manager.findFragmentById(R.id.menusFragment);
                ft.show(bottomFragment);
                ft.commit();

            }
        });

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