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

        ArrayList results = new ArrayList();

        for (Address cn : address) {




        }
        ListViewAddress.setAdapter(Address);


            ArrayList image_details = getListData();
            final ListView lv1 = (ListView) findViewById(R.id.custom_list);
            lv1.setAdapter(new CustomListAdapter(this, image_details));
            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    Object o = lv1.getItemAtPosition(position);
                    NewsItem newsData = (NewsItem) o;
                    Toast.makeText(MainActivity.this, "Selected :" + " " + newsData, Toast.LENGTH_LONG).show();
                }

            });


            monthsListView.setAdapter(arrayAdapter);
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);
    }
}
