package com.example.tbnaaproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import javax.xml.validation.Schema;

public class TbnaaDatabase  {

    //Define the db Schema
    private static final String databaseName = "TbnaaDB";
    private static final int databaseVersion = 1;
    //Cat table
    private static String catTableName = "Cat";
    private static final String createCatTable = "CREATE TABLE Cat (catId INTEGER PRIMARY KEY AUTOINCREMENT,catImage BLOB, catName TEXT,catAge TEXT, catCity TEXT, catGender TEXT,vaccinated TEXT, neutered TEXT, healtheCare TEXT, catStory TEXT, isApproved TEXT, isAdoptted TEXT);";

    //basics
    private final Context ct;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    //Constructor
    public TbnaaDatabase(Context context) {
        this.ct = context;
        dbHelper = new DatabaseHelper(ct);

    }

    //-------------------------------------------------------------------------

    public class DatabaseHelper extends SQLiteOpenHelper {
        //Constructor
        DatabaseHelper(Context c) {
            super(c, databaseName, null, databaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try {
                //Create Tables here

                //Cat Table
                sqLiteDatabase.execSQL(createCatTable);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            database.execSQL("DROP TABLE IF EXISTS CAT");
            onCreate(database);

        }
    }// End of DatabaseHelper

    //Connect to the Database
    public TbnaaDatabase connect() throws SQLException {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    //Disconnect from the Database
    public void disconnect() {
        dbHelper.close();
    }

    // addCat method to insert cats into Cat table
    public long addCat(byte[] catImage, String catName, String catAge,
                       String catCity,
                       String catGender, String vaccinated,
                       String neutered, String healtheCare, String catStory) {

        ContentValues cv = new ContentValues();

        cv.put("catImage", catImage);
        cv.put("catName", catName);
        cv.put("catAge", catAge);
        cv.put("catCity", catCity);
        cv.put("catGender", catGender);
        cv.put("vaccinated", vaccinated);
        cv.put("neutered", neutered);
        cv.put("healtheCare", healtheCare);
        cv.put("catStory", catStory);
        cv.put("isApproved", "null");
        cv.put("isAdoptted", "null");

        this.connect();
        return database.insert(catTableName, null, cv);

    }
}































