package com.example.tbnaaproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import javax.xml.validation.Schema;

public class TbnaaDatabase  {

    //Define the db Schema
    private static final String databaseName = "TbnaaDB";
    private static final int databaseVersion = 13;
    //Cat table
    private static String catTableName = "Cat";
    private static final String createCatTable = "CREATE TABLE IF NOT EXISTS Cat (catId INTEGER PRIMARY KEY AUTOINCREMENT,catImage BLOB, catName TEXT,catAge TEXT, catCity TEXT, catGender TEXT,vaccinated TEXT, neutered TEXT, healtheCare TEXT, catStory TEXT, isApproved TEXT, isAdoptted TEXT);";

    //Adoption  table
    private static String adoptionTableName = "AdaptionForm";
    private static final String createAdaptionFormTable = "CREATE TABLE IF NOT EXISTS AdaptionForm(adoptionFormId INTEGER PRIMARY KEY AUTOINCREMENT, hadAdopted TEXT,adoptedStory TEXT, extraPhoneNo TEXT, liveAlone TEXT,haveKids TEXT, perantApproval TEXT, allergic TEXT, catPlacement TEXT,isOwner TEXT,clinicName TEXT, pledge TEXT, isApproved TEXT);";

    //shareableCatInfo Table for ContentProvider
    private static String shareableCatInfoTableName = "shareableCatInfo";
    private static final String createShareableCatInfo = "CREATE TABLE IF NOT EXISTS shareableCatInfo(cat_Id INTEGER PRIMARY KEY AUTOINCREMENT, CatName TEXT,CatLocation TEXT);";
    //basics
    private final Context ct;
    private DatabaseHelper dbHelper;
    static SQLiteDatabase database;

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
                //adaption Table
                sqLiteDatabase.execSQL(createAdaptionFormTable);
                //create Shareable Cat Info Table
                sqLiteDatabase.execSQL(createShareableCatInfo);

                //sqLiteDatabase.execSQL("INSERT INTO "+ catTableName  + " VALUES(1,'','Abby', '7 Years.','Dammam','Female','Yes','Yes','healthy','A lovely homeless cat that I found near a panda hypermarket. it loves other cats ','null','null');");

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Cat");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS AdaptionForm");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS shareableCatInfo");
            onCreate(sqLiteDatabase);

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

        this.connect();

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


        return database.insert(catTableName, null, cv);

    }

    // addAdaptionForm method to insert Adaption Form into AdaptionForm table
    public long addAdaptionForm( String hadAdopted , String adoptedStory ,
                       String extraPhoneNo , String liveAlone , String haveKids ,
                       String perantApproval , String allergic, String catPlacement,
                                 String isOwner,String clinicName,String pledge ) {

        ContentValues cv = new ContentValues();

        cv.put("hadAdopted", hadAdopted );
        cv.put("adoptedStory", adoptedStory );
        cv.put("extraPhoneNo", extraPhoneNo );
        cv.put("liveAlone", liveAlone );
        cv.put("haveKids", haveKids );
        cv.put("perantApproval", perantApproval );
        cv.put("allergic", allergic);
        cv.put("catPlacement", catPlacement );
        cv.put("isOwner", isOwner );
        cv.put("clinicName", clinicName);
        cv.put("pledge", pledge );
        cv.put("isApproved", "null");

        this.connect();
        return database.insert(adoptionTableName, null, cv);

    }

    public Cursor retrieveCatInfo(int id){
        this.connect();
        Cursor c= database.rawQuery("SELECT catImage, catName, catAge , catCity , catGender, vaccinated,neutered ,catStory,isAdoptted FROM Cat WHERE catId="+id+";",null);
        return c;
    }

    public long addSharableCatInfo(ContentValues values){
        this.connect();
        return database.insert(shareableCatInfoTableName, null, values);
    }


}































