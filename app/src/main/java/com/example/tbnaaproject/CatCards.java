package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.tbnaaproject.adapters.CatCardsAdapter;
import com.example.tbnaaproject.R;
import com.example.tbnaaproject.models.Cats;

import java.util.ArrayList;

public class CatCards extends AppCompatActivity {

    GridView gridView;
    ArrayList<Cats> catsList;
    CatCardsAdapter catAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_cards);

        gridView = (GridView) findViewById(R.id.catsGridView);

        TbnaaDatabase databaseHelper = new TbnaaDatabase(CatCards.this);
        catsList = new ArrayList<Cats>();

        catsList = databaseHelper.getAllCats();
        catAdapter = new CatCardsAdapter(CatCards.this, catsList);
        gridView.setAdapter(catAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Open cat profile page
                Cats theCat = (Cats) parent.getItemAtPosition(position);

                Bundle b = new Bundle();
                b.putSerializable("bundleobj", theCat);

                Intent intent = new Intent(CatCards.this, CatProfile.class);
                intent.putExtra("catID", b);
                startActivity(intent);

            }
        });

    }
}