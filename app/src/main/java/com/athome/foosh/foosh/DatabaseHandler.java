package com.athome.foosh.foosh;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Bob on 2014/08/19.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "addressManager";
    public static final String TABLE_ADDRESS = "address";
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "Name";
    public static final String KEY_PH_NO = "Phone_number";
    public static final String KEY_LastName = "LastName";
    public static final String KEY_Email = "Email";
    public static final String KEY_Address = "HomeAddress";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ADDRESS_TABLE = "CREATE TABLE " + TABLE_ADDRESS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_LastName + " TEXT" + KEY_Email + " TEXT" + KEY_PH_NO + " TEXT" + KEY_Address + " TEXT" + ")";
        db.execSQL(CREATE_ADDRESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);

        onCreate(db);
    }


}
