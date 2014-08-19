package repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.athome.foosh.foosh.Address;
import com.athome.foosh.foosh.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bob on 2014/08/19.
 */
public class DatasourceDAOImpl implements DatasourceDAO {
    private DatabaseHandler dbHelper;

    public void addAddress(Address address) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_NAME, address.getName()); // Contact Name
        values.put(DatabaseHandler.KEY_PH_NO, address.getPhoneNumber()); // Contact Phone Number

        // Inserting Row
        db.insert(DatabaseHandler.TABLE_ADDRESS, null, values);
        db.close(); // Closing database connection
    }

    public Address getAddress(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHandler.TABLE_ADDRESS, new String[] { DatabaseHandler.KEY_ID,
                        DatabaseHandler.KEY_NAME, DatabaseHandler.KEY_PH_NO }, DatabaseHandler.KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Address address = new Address(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return address;
    }

    public List<Address> getAllAddress() {
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
                address.setPhoneNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(address);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public int getAddressCount() {
        String countQuery = "SELECT  * FROM " + DatabaseHandler.TABLE_ADDRESS;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public int updateAddress(Address address) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_NAME, address.getName());
        values.put(DatabaseHandler.KEY_PH_NO, address.getPhoneNumber());

        // updating row
        return db.update(DatabaseHandler.TABLE_ADDRESS, values, DatabaseHandler.KEY_ID + " = ?",
                new String[] { String.valueOf(address.getID()) });
    }

    public void deleteAddress(Address address) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHandler.TABLE_ADDRESS, DatabaseHandler.KEY_ID + " = ?",
                new String[] { String.valueOf(address.getID()) });
        db.close();
    }
}
