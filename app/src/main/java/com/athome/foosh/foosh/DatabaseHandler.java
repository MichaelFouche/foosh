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
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "addressManager.db";
    public static final String TABLE_ADDRESS = "AMaddress";
    public static final String KEY_ID = "DBID";
    public static final String KEY_NAME = "DBName";
    public static final String KEY_PH_NO = "DBPhone_number";
    public static final String KEY_LASTNAME = "DBLastName";
    public static final String KEY_EMAIL = "DBEmail";
    public static final String KEY_ADDRESS = "DBHomeAddress";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ADDRESS_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_ADDRESS + "("
                + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
                + KEY_NAME + " TEXT,"
                + KEY_LASTNAME + " TEXT, "
                + KEY_EMAIL + " TEXT, "
                + KEY_PH_NO + " TEXT, "
                + KEY_ADDRESS + " TEXT" + ");";
        db.execSQL(CREATE_ADDRESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);

        onCreate(db);
    }


}
