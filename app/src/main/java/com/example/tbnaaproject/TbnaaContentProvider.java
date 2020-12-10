package com.example.tbnaaproject;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.util.HashMap;

public class TbnaaContentProvider extends ContentProvider {
    public TbnaaContentProvider() {
    }
    // defining authority so that other application can access it
    static final String PROVIDER_NAME = "com.tbnaa.content.provider";
    // defining content URI
    static final String URI = "content://" + PROVIDER_NAME + "/tbnaa";
    // parsing the content URI
    static final Uri CONTENT_URI = Uri.parse(URI);
    //coloumns for db
    static final String CatID = "cat_Id";
    static final String CatLocation = "CatLocation";
    static final String CatName = "CatName";
    // to use it for the uriMatcher
    static final int uriCode = 1;
    static final UriMatcher uriMatcher;
    SQLiteDatabase database;
    TbnaaDatabase tbnaadbHelper;
    private static HashMap<String, String> values;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "tbnaa", uriCode); //com.tbnaa.content.provider/tbnaa
        uriMatcher.addURI(PROVIDER_NAME, "tbnaa/*", uriCode);//com.tbnaa.content.provider/tbnaa/# (any number)
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case uriCode:
                count = TbnaaDatabase.database.delete("shareableCatInfo", selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long rowID = tbnaadbHelper.addSharableCatInfo(values);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLiteException("Failed to add a record into " + uri);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        tbnaadbHelper = new TbnaaDatabase(context);
        tbnaadbHelper.connect();
        if (tbnaadbHelper.connect()!= null) {
            return true;
        }
        return false;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case uriCode:
                return "vnd.android.cursor.dir/tbnaa";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }



    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables("shareableCatInfo");
        switch (uriMatcher.match(uri)) {
            case uriCode:
                qb.setProjectionMap(values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder == "") {
            sortOrder = CatID;
        }

        Cursor c = qb.query(TbnaaDatabase.database, projection, selection, selectionArgs, null,
                null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case uriCode:
                count = TbnaaDatabase.database.update("shareableCatInfo", values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}