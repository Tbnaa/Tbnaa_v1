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

public class TbnaaDatabase {

    //Define the db Schema
    private static final String databaseName = "TbnaaDB";
    private static final int databaseVersion = 13;
  
    //Cat table
    private static String catTableName = "Cat";
    private static final String createCatTable = "CREATE TABLE Cat (catID INTEGER PRIMARY KEY AUTOINCREMENT" +
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


    //shareableCatInfo Table for ContentProvider
    private static String shareableCatInfoTableName = "shareableCatInfo";
    private static final String createShareableCatInfo = "CREATE TABLE IF NOT EXISTS shareableCatInfo(cat_Id INTEGER PRIMARY KEY AUTOINCREMENT, CatName TEXT,CatLocation TEXT);";

    //Adoption  table
    private static String adoptionTableName = "AdaptionForm";
    private static final String createAdaptionFormTable = "CREATE TABLE IF NOT EXISTS AdaptionForm(adoptionFormId INTEGER PRIMARY KEY AUTOINCREMENT, uID INTEGER, hadAdopted TEXT,adoptedStory TEXT, extraPhoneNo TEXT, liveAlone TEXT,haveKids TEXT, perantApproval TEXT, allergic TEXT, catPlacement TEXT,isOwner TEXT,clinicName TEXT, pledge TEXT, isApproved TEXT);";

    //Adoption  table
    private static String addingTableName = "AdaptionForm";
    private static final String createAddingFormTable = "CREATE TABLE IF NOT EXISTS AdaptionForm(adoptionFormId INTEGER PRIMARY KEY AUTOINCREMENT, hadAdopted TEXT,adoptedStory TEXT, extraPhoneNo TEXT, liveAlone TEXT,haveKids TEXT, perantApproval TEXT, allergic TEXT, catPlacement TEXT,isOwner TEXT,clinicName TEXT, pledge TEXT, isApproved TEXT);";



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
                //User Table
                sqLiteDatabase.execSQL(createUserTable);
                //Admin Table
                sqLiteDatabase.execSQL(createAdminTable);
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
    public long addAdaptionForm(String hadAdopted, String adoptedStory,
                                String extraPhoneNo, String liveAlone, String haveKids,
                                String perantApproval, String allergic, String catPlacement,
                                String isOwner, String clinicName, String pledge) {

        ContentValues cv = new ContentValues();

        cv.put("hadAdopted", hadAdopted);
        cv.put("adoptedStory", adoptedStory);
        cv.put("extraPhoneNo", extraPhoneNo);
        cv.put("liveAlone", liveAlone);
        cv.put("haveKids", haveKids);
        cv.put("perantApproval", perantApproval);
        cv.put("allergic", allergic);
        cv.put("catPlacement", catPlacement);
        cv.put("isOwner", isOwner);
        cv.put("clinicName", clinicName);
        cv.put("pledge", pledge);
        cv.put("isApproved", "null");

        this.connect();
        return database.insert(adoptionTableName, null, cv);

    }

    public Cursor retrieveCatInfo(int id) {
        this.connect();
        Cursor c = database.rawQuery("SELECT catImage, catName, catAge , catCity , catGender, vaccinated,neutered ,catStory,isAdoptted FROM Cat WHERE catId=" + id + ";", null);
        return c;
    }

    public long addSharableCatInfo(ContentValues values) {
        this.connect();
        return database.insert(shareableCatInfoTableName, null, values);
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


    // getAddingCatAdminInfo method to get cats image and name info from Cat table to show in Add cat request
    // also, getting userName from User table
    public ArrayList<AddingAdminWord> getAddingCatAdminInfo() {

        this.connect();
        String query = "SELECT catId, catImage, catName FROM Cat";
        ArrayList<AddingAdminWord> setOfCats = new ArrayList<AddingAdminWord>();

        Cursor c = database.rawQuery(query, null);
        if (c != null) {
            while (c.moveToNext()) {
                byte[] imageOfCatt = c.getBlob(c.getColumnIndex("catImage"));
                String nameOfCat = c.getString(c.getColumnIndex("catName"));
                int catID = c.getInt(c.getColumnIndex("catId"));

                AddingAdminWord cat = new AddingAdminWord();

                cat.setImage(imageOfCatt);
                cat.setName(nameOfCat);

                setOfCats.add(cat);
            }
        }

        return setOfCats;

    }

    //Update isApproved record from cat table
    public void UpdateIsApproved(int id) {
        this.connect();
        String query = "Update Cat Set isApproved='true' Where catId = id";
        }
    //Update isApproved record from cat table
    public void UpdateIsApprovedReject(int id) {
        this.connect();
        String query = "Update Cat Set isApproved='false' Where catId = id";
    }

//    public boolean updateData(String id,String name,String username,String email,String phone, String city) {
//        this.connect();
//        database = dbHelper.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Col_1,id);
//        contentValues.put(COL_2,name);
//        contentValues.put(COL_3,username);
//        contentValues.put(COL_4,email);
//        contentValues.put(COL_5,phone);
//        contentValues.put(COL_6,city);
//        database.update(adminTableName, contentValues, "ID = ?",new String[] { id });
//        return true;
//    }


    // getAllCats method to get all cats general info from Cat table to show in gallary
    public ArrayList<Cats> getRequestedCats(String userid) {
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

    public long addAdmin(byte[] aImage, String aPassword, String aFirstName,
                        String aLastName, String aGender,
                        String aEmail, String aPhone) {

        ContentValues cv = new ContentValues();

        cv.put("aPassword", aPassword);
        cv.put("aImage", aImage);
        cv.put("aFirstName", aFirstName);
        cv.put("aLastName", aLastName);
        cv.put("aGender", aGender);
        cv.put("aEmail", aEmail);
        cv.put("aPhone", aPhone);

        this.connect();
        return database.insert(userTableName, null, cv);
    }
}
