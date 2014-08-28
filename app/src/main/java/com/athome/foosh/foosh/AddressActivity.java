package com.athome.foosh.foosh;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import repository.DatasourceDAO;
import repository.DatasourceDAOImpl;

/**
 * Created by Bob on 2014/08/19.
 */
public class AddressActivity extends Activity implements AdapterView.OnItemClickListener {
    ListView ListViewAddress;
    ArrayAdapter arrayAdapter;
    DatasourceDAO db = new DatasourceDAOImpl(this);
    List<Address> address;
    ArrayList mNameList;
    TextView txtVName, txtVSurname, txtVEmail, txtVCell, txtVAddress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        db = new DatasourceDAOImpl(this);
        ListViewAddress = (ListView) findViewById(R.id.listView);
        ListViewAddress.setOnItemClickListener(this);
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        address = db.getAllAddress();


        mNameList = new ArrayList();

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mNameList);

        Log.i("Size of database",address.size()+"");


        for (Address cn : address) {
            mNameList.add( cn.getName() + ", " + cn.getPhoneNumber());
            String log = "Name: " + cn.getName()+ " ,Surname: " + cn.getLastName() + " ,Phone: " + cn.getPhoneNumber() +  " ,Email: " + cn.getEmail()+ " ,Address: " + cn.getAddress();
            // Writing Contacts to log
            Log.i("Address: ", log);
            arrayAdapter.notifyDataSetChanged();

        }
        ListViewAddress.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //List<Address> address = db.getAllAddress();
        for (Address ad : address) {
            if(ad.getID()==(position+1)){
                txtVName = (TextView) findViewById(R.id.txtVName);
                txtVSurname =(TextView) findViewById(R.id.txtVSurname);
                txtVEmail = (TextView) findViewById(R.id.txtVEmail);
                txtVCell = (TextView) findViewById(R.id.txtVCell);
                txtVAddress = (TextView) findViewById(R.id.txtVAddress);

                txtVName.setText("Name "+ad.getName());
                txtVSurname.setText("Last Name: "+ad.getLastName());
                txtVEmail.setText("Email: "+ad.getEmail());
                txtVCell.setText("Cell: "+ad.getPhoneNumber());
                txtVAddress.setText("Address: "+ad.getAddress());;
            }
        }
    }
}
