package com.example.tbnaaproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.Nullable;
import com.example.tbnaaproject.models.Cats;
import java.sql.Blob;
import java.util.ArrayList;
import javax.xml.validation.Schema;

public class TbnaaDatabase  {

    //Define the db Schema
    private static final String databaseName = "TbnaaDB";
    private static final int databaseVersion = 1;

    //Cat table
    private static String catTableName = "Cat";
    private static final String createCatTable = "CREATE TABLE Cat (catId INTEGER PRIMARY KEY AUTOINCREMENT" +
            ",catImage BLOB, catName TEXT,catAge TEXT, catCity TEXT, catGender TEXT,vaccinated TEXT, neutered TEXT" +
            ", healtheCare TEXT, catStory TEXT, isApproved TEXT, isAdoptted TEXT);";

    //User table
    private static String userTableName = "User";
    private static final String createUserTable = "CREATE TABLE User (uID INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", upassword TEXT, uImage BLOB, uFirstName TEXT, uLastName TEXT, uGender TEXT, socialState TEXT, uEmail TEXT" +
            ", uPhone TEXT, uCity TEXT);";

    //Admin table
    private static String adminTableName = "Admin";
    private static final String createAdminTable = "CREATE TABLE Admin (aID INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", aPassword TEXT, aImage BLOB, aFirstName TEXT, aLastName TEXT, aGender TEXT, aEmail TEXT, aPhone TEXT);";

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
                //User Table
                sqLiteDatabase.execSQL(createUserTable);
                //Admin Table
                sqLiteDatabase.execSQL(createAdminTable);
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

    // getAllCats method to get all cats general info from Cat table to show in gallary
    public ArrayList<Cats> getAllCats() {
        this.connect();
        //return database.rawQuery("SELECT catImage,catName, catCity, catGender FROM Cat", null);

        String query = "SELECT catImage, catName, catCity, catGender FROM Cat";
        ArrayList<Cats> setOfCats = new ArrayList<Cats>();

        Cursor c = database.rawQuery(query, null);
        if (c != null) {
            while (c.moveToNext()) {
                byte[] imageOfCatt = c.getBlob(c.getColumnIndex("catImage"));
                //Bitmap cattImage = BitmapFactory.decodeByteArray(imageOfCatt, 0, imageOfCatt.length);

                String nameOfCat = c.getString(c.getColumnIndex("catName"));
                String cityOfCat = c.getString(c.getColumnIndex("catCity"));
                String genderOfCat = c.getString(c.getColumnIndex("catGender"));

                Cats cat = new Cats();
                cat.setImage(imageOfCatt);
                cat.setName(nameOfCat);
                cat.setCity(cityOfCat);
                cat.setGender(genderOfCat);

                setOfCats.add(cat);
            }
        }
        return setOfCats;
    }

    // getAllCats method to get all cats general info from Cat table to show in gallary
    public ArrayList<Cats> getRequestedCats(String useridgi) {
        this.connect();
        //return database.rawQuery("SELECT catImage,catName, catCity, catGender FROM Cat", null);

        String query = "SELECT catImage, catName, catCity, catGender FROM Cat";
        ArrayList<Cats> setOfCats = new ArrayList<Cats>();

        Cursor c = database.rawQuery(query, null);
        if (c != null) {
            while (c.moveToNext()) {
                byte[] imageOfCatt = c.getBlob(c.getColumnIndex("catImage"));
                //Bitmap cattImage = BitmapFactory.decodeByteArray(imageOfCatt, 0, imageOfCatt.length);

                String nameOfCat = c.getString(c.getColumnIndex("catName"));
                String cityOfCat = c.getString(c.getColumnIndex("catCity"));
                String genderOfCat = c.getString(c.getColumnIndex("catGender"));

                Cats cat = new Cats();
                cat.setImage(imageOfCatt);
                cat.setName(nameOfCat);
                cat.setCity(cityOfCat);
                cat.setGender(genderOfCat);

                setOfCats.add(cat);
            }
        }
        return setOfCats;
    }
    public long addUser(byte[] uImage, String uPassword, String uFirstName,
                       String uLastName, String uGender, String socialState,
                       String uEmail, String uPhone, String uCity) {

        ContentValues cv = new ContentValues();

        cv.put("uPassword", uPassword);
        cv.put("uImage", uImage);
        cv.put("uFirstName", uFirstName);
        cv.put("uLastName", uLastName);
        cv.put("uGender", uGender);
        cv.put("socialState", socialState);
        cv.put("uEmail", uEmail);
        cv.put("uPhone", uPhone);
        cv.put("uCity", uCity);

        this.connect();
        return database.insert(userTableName, null, cv);
    }


}