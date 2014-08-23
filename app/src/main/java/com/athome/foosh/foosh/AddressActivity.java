package com.athome.foosh.foosh;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import repository.DatasourceDAO;
import repository.DatasourceDAOImpl;

/**
 * Created by Bob on 2014/08/19.
 */
public class AddressActivity extends Activity {
    ListView ListViewAddress;
    ArrayAdapter arrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        DatasourceDAO db = new DatasourceDAOImpl(this);
        ListViewAddress = (ListView) findViewById(R.id.listView);
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Address> address = db.getAllAddress();


        ArrayList mNameList = new ArrayList();

        arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                mNameList);



        for (Address cn : address) {
            mNameList.add( cn.getName() + ", " + cn.getPhoneNumber());
           // String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
           // Log.d("Name: ", log);


        }
        ListViewAddress.setAdapter(arrayAdapter);

    }
}
