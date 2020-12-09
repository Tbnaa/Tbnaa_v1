package com.example.tbnaaproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

    TextView catName, userName;
    ImageView imge;
    ImageButton yes,no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_admin);
        TbnaaDBHelper=new AddingAdminAdapter(this, words);
        TbnaaDBHelper.open();
        // Create a list of words
        final ArrayList<AddingAdminWord> words = new ArrayList<AddingAdminWord>();
        TbnaaDBHelper=new AddingAdminAdapter(this);
        TbnaaDBHelper.open();

//        words.add(new AddingAdminWord("Alex", "Sara", R.drawable.img_cat_alex));
//        words.add(new AddingAdminWord("Lucy", "Maha", R.drawable.img_cat_lucy));
//        words.add(new AddingAdminWord("Maya", "Noha", R.drawable.img_cat_maya));
//        words.add(new AddingAdminWord("Nader", "Emmy", R.drawable.img_cat_nader));
//        words.add(new AddingAdminWord("Shasha", "Nana", R.drawable.img_cat_sasha));
//        words.add(new AddingAdminWord("Alex", "Farah", R.drawable.img_cat_alex));
//        words.add(new AddingAdminWord("Lucy", "Hamad", R.drawable.img_cat_lucy));
//        words.add(new AddingAdminWord("Maya", "Mohammed", R.drawable.img_cat_maya));
//        words.add(new AddingAdminWord("Nader", "Amr", R.drawable.img_cat_nader));
//        words.add(new AddingAdminWord("Shasha", "Omar", R.drawable.img_cat_sasha));

        AddingAdminAdapter adapter = new AddingAdminAdapter(this, words);
        final ListView listView = (ListView) findViewById(R.id.activity_adding_cats_requests_list);
        listView.setAdapter(adapter);


        // Get SQLite database query cursor.
        userInfoDBManager = new UserInfoDBManager(getApplicationContext());
        userInfoDBManager.open();
        Cursor cursor = userInfoDBManager.getAllAccountCursor();

        // Create a new SimpleCursorAdapter.
        listViewDataAdapter = new SimpleCursorAdapter(this, R.layout.activity_user_account_list_view_item, cursor, fromColumnArr, toViewIdArr, CursorAdapter.FLAG_AUTO_REQUERY);
        //listViewDataAdapter.notifyDataSetChanged();

        // Set simple cursor adapter to list view.
        userAccountListView.setAdapter(listViewDataAdapter);

        // When list view item is clicked.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get list view item SQLiteCursor object.
                Object clickItemObject = adapterView.getAdapter().getItem(position);
                SQLiteCursor cursor = (SQLiteCursor)clickItemObject;

                // Get row column data.
                int rowId = cursor.getInt(cursor.getColumnIndex(UserInfoDBManager.TABLE_ACCOUNT_COLUMN_ID));
                String userName = cursor.getString(cursor.getColumnIndex(UserInfoDBManager.TABLE_ACCOUNT_COLUMN_USERNAME));
                String password = cursor.getString(cursor.getColumnIndex(UserInfoDBManager.TABLE_ACCOUNT_COLUMN_PASSWORD));
                String email = cursor.getString(cursor.getColumnIndex(UserInfoDBManager.TABLE_ACCOUNT_COLUMN_EMAIL));




                AddingAdminWord word = words.get(position);
                Cursor result=DBHelper.getAllCatData();
                //show erreor message
                if(result.getCount()==0) return;
                while (result.moveToNext()) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(result.getPosition());
                }
                //    result.getPosition();
                //     AddingAdminWord word = words.get(position);

                //TODO: Open more INFO
            }
        });
    }
}