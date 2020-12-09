package com.example.tbnaaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AddingAdmin extends AppCompatActivity {
TbnaaDatabase.DatabaseHelper DBHelper;

    private TbnaaDatabase TbnaaDBHelper;

    private ListView listView;

    private AddingAdminAdapter addingAdminAdapter;

    String catname[];
    int images[];
    TextView catName1, userName1;
    ImageView imge1;
    ImageButton yes,no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_admin);
        listView = (ListView) findViewById(R.id.activity_adding_cats_requests_list);
        catName1 = (TextView) listView.findViewById(R.id.catName);
        userName1 = (TextView) listView.findViewById(R.id.userName);
       // imge1 = (ImageView) listView.findViewById(R.id.imge);
        yes = (ImageButton)listView.findViewById(R.id.yes);
        no = (ImageButton)listView.findViewById(R.id.no);

        // Create a list of words
        final ArrayList<Integer> image = new ArrayList<>();
        final ArrayList<String> words= new ArrayList<>();
        ArrayList<AddingAdminAdapter> Word= new ArrayList<>();
        Cursor result=TbnaaDBHelper.getAllCatData();
        if(result.getCount()==0) return;
        while (result.moveToNext()) {
//            image.add(result.getPosition());
//            words.add(result.getString(2));
            catName1 = (TextView) listView.findViewById(R.id.catName);
        }


        //AddingAdminAdapter adapter = new AddingAdminAdapter(this, );
       // listView.setAdapter(adapter);

       
        
       

        // Get SQLite database query cursor.
        TbnaaDBHelper = new TbnaaDatabase(getApplicationContext());
        TbnaaDBHelper.connect();
        Cursor cursor = TbnaaDBHelper.getAllCatData();

      

        // When list view item is clicked.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get list view item SQLiteCursor object.
                Object clickItemObject = adapterView.getAdapter().getItem(position);
                SQLiteCursor cursor = (SQLiteCursor)clickItemObject;

                // Get row column data.


//                Integer word = words.get(position);
                //show erreor message
                //    result.getPosition();
                //     AddingAdminWord word = words.get(position);

                //TODO: Open more INFO
            }
        });
 
    }

//    @Override
//    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
//        holder.catName.setText(catname[position]);
//        holder.catImage.setImageResource(images[position]);
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//        TextView catName;
//        ImageView catImage;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            catName = itemView.findViewById(R.id.catName);
//            catImage = itemView.findViewById(R.id.image);
//
//        }
}