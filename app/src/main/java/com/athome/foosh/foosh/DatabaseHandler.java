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
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "addressManager";
    public static final String TABLE_ADDRESS = "address";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PH_NO = "phone_number";
    public static final String KEY_LastName = "phone_number";
    public static final String KEY_Email = "phone_number";
    public static final String KEY_Address = "phone_number";


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

    /*public ArrayList<Object> getRowAsArray(String rowID)
    {
        // create an array list to store data from the database row.
        // I would recommend creating a JavaBean compliant object
        // to store this data instead.  That way you can ensure
        // data types are correct.
        ArrayList<Object> rowArray = new ArrayList<Object>();
        Cursor cursor;

        try
        {
            // this is a database call that creates a "cursor" object.
            // the cursor object store the information collected from the
            // database and is used to iterate through the data.
            cursor = mDb.query
                    (
                            SQLITE_TABLE,
                            new String[] { KEY_SURNAME, KEY_NAME, KEY_CELLNUM,KEY_EMAILADDR, KEY_HOMEADDR  },
                            KEY_CELLNUM + " = '" +rowID+"'",
                            null, null, null, null, null
                    );
            Log.w("quaery", String.valueOf(cursor.getColumnNames()));
            // move the pointer to position zero in the cursor.
            cursor.moveToFirst();

            // if there is data available after the cursor's pointer, add
            // it to the ArrayList that will be returned by the method.
            if (!cursor.isAfterLast())
            {
                do
                {
                    rowArray.add(cursor.getString(0));
                    rowArray.add(cursor.getString(1));
                    rowArray.add(cursor.getString(2));
                    rowArray.add(cursor.getString(3));
                    rowArray.add(cursor.getString(4));
                }
                while (cursor.moveToNext());
            }

            // let java know that you are through with the cursor.
            cursor.close();
        }
        catch (SQLException e)
        {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }

        // return the ArrayList containing the given row from the database.
        return rowArray;
    }*/

}
