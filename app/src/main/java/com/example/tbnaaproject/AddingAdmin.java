package com.example.tbnaaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tbnaaproject.adapters.CatCardsAdapter;
import com.example.tbnaaproject.models.Cats;

import java.util.ArrayList;

public class AddingAdmin extends AppCompatActivity {
TbnaaDatabase.DatabaseHelper DBHelper;

    private TbnaaDatabase TbnaaDBHelper;

    ListView listView;
    ArrayList<Cats> catsList;
    AddingAdminAdapter catAdapter;

    ImageButton yes,no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_admin);

        listView = (ListView) findViewById(R.id.activity_adding_cats_requests_list);

        TbnaaDatabase databaseHelper = new TbnaaDatabase(AddingAdmin.this);
        catsList = new ArrayList<Cats>();

        catsList = databaseHelper.getAddingCatAdminInfo();
        catAdapter = new AddingAdminAdapter(AddingAdmin.this, catsList);
        listView.setAdapter(catAdapter);


//        listView = (ListView) findViewById(R.id.activity_adding_cats_requests_list);
//        catName1 = (TextView) listView.findViewById(R.id.catName);
//        userName1 = (TextView) listView.findViewById(R.id.userName);
//       // imge1 = (ImageView) listView.findViewById(R.id.imge);
//        yes = (ImageButton)listView.findViewById(R.id.yes);
//        no = (ImageButton)listView.findViewById(R.id.no);



//
        // When list view item is clicked.
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get list view item SQLiteCursor object.
                Object clickItemObject = adapterView.getAdapter().getItem(position);
                SQLiteCursor cursor = (SQLiteCursor)clickItemObject;



            }
        });
 
    }

}