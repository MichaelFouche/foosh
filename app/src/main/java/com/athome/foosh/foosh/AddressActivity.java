package com.athome.foosh.foosh;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import repository.DatasourceDAO;
import repository.DatasourceDAOImpl;

/**
 * Created by Bob on 2014/08/19.
 */
public class AddressActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        DatasourceDAO db = new DatasourceDAOImpl(this);

        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addAddress(new Address("Ravi", "9100000000"));
        db.addAddress(new Address("Srinivas", "9199999999"));
        db.addAddress(new Address("Tommy", "9522222222"));
        db.addAddress(new Address("Karthik", "9533333333"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Address> address = db.getAllAddress();

        for (Address cn : address) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }
}
