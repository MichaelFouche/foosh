package repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.athome.foosh.foosh.Address;
import com.athome.foosh.foosh.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bob on 2014/08/19.
 */
public class DatasourceDAOImpl implements DatasourceDAO {
    private DatabaseHandler dbHelper;
    SQLiteDatabase db;

    public DatasourceDAOImpl(Context context){
        dbHelper =  new DatabaseHandler(context);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    public void addAddress(Address address) {

        open();

       ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_NAME, address.getName()); // Contact Name
        values.put(DatabaseHandler.KEY_LASTNAME, address.getLastName()); // Contact Phone Number
        values.put(DatabaseHandler.KEY_EMAIL, address.getEmail());
        values.put(DatabaseHandler.KEY_PH_NO, address.getPhoneNumber());
        values.put(DatabaseHandler.KEY_ADDRESS, address.getAddress());

        /*
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_NAME, "Mike"); // Contact Name
        values.put(DatabaseHandler.KEY_LastName, "Fouche"); // Contact Phone Number
        values.put(DatabaseHandler.KEY_Email, "fou@sa");
        values.put(DatabaseHandler.KEY_PH_NO, "072");
        values.put(DatabaseHandler.KEY_Address, "nederburg");
*/
        // Inserting Row
        db.insert(DatabaseHandler.TABLE_ADDRESS, null, values);
        Log.i("Added:", " Values: " + "Id: " + address.getID() + " ,Name: " + address.getName()+ " ,Surname: " + address.getLastName() + " ,Email: " + address.getEmail()+ " ,Phone: " + address.getPhoneNumber() +   " ,Address: " + address.getAddress());
        close(); // Closing database connection
    }

    public Address getAddress(int id) {
        open();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHandler.TABLE_ADDRESS, new String[] { DatabaseHandler.KEY_ID,
                        DatabaseHandler.KEY_NAME, DatabaseHandler.KEY_PH_NO }, DatabaseHandler.KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Address address = new Address(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        close();
        return address;
    }

    public List<Address> getAllAddress() {
        open();
        List<Address> contactList = new ArrayList<Address>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHandler.TABLE_ADDRESS;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Address address = new Address();
                address.setID(Integer.parseInt(cursor.getString(0)));
                address.setName(cursor.getString(1));
                address.setLastName(cursor.getString(2));
                address.setEmail(cursor.getString(3));
                address.setPhoneNumber(cursor.getString(4));
                address.setAddress(cursor.getString(5));
                // Adding contact to list
                contactList.add(address);
            } while (cursor.moveToNext());
        }

        // return contact list
        close();
        return contactList;
    }

    public int getAddressCount() {
        open();
        String countQuery = "SELECT  * FROM " + DatabaseHandler.TABLE_ADDRESS;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        close();
        // return count
        return cursor.getCount();
    }

    public int updateAddress(Address address) {
        open();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_NAME, address.getName()); // Contact Name
        values.put(DatabaseHandler.KEY_LASTNAME, address.getLastName()); // Contact Phone Number
        values.put(DatabaseHandler.KEY_EMAIL, address.getEmail());
        values.put(DatabaseHandler.KEY_PH_NO, address.getPhoneNumber());
        values.put(DatabaseHandler.KEY_ADDRESS, address.getAddress());

        // updating row
        close();
        return db.update(DatabaseHandler.TABLE_ADDRESS, values, DatabaseHandler.KEY_ID + " = ?",
                new String[] { String.valueOf(address.getID()) });

    }

    public void deleteAddress(Address address) {
        open();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHandler.TABLE_ADDRESS, DatabaseHandler.KEY_ID + " = ?",
                new String[] { String.valueOf(address.getID()) });
        close();
    }
}
