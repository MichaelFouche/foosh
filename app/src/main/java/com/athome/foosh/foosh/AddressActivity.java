package com.athome.foosh.foosh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import repository.DatasourceDAO;
import repository.DatasourceDAOImpl;

/**
 * Created by Bob on 2014/08/19.
 */
public class AddressActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {
    ListView ListViewAddress;
    ArrayAdapter arrayAdapter;
    DatasourceDAO db = new DatasourceDAOImpl(this);
    List<Address> address;
    ArrayList mNameList;
    Button btnAddNewContact;
    Button btnDeleteContact;
    Button btnUpdateContact;
    TextView txtVName, txtVSurname, txtVEmail, txtVCell, txtVAddress;
    int currentActiveItemID,currentActiveItemNum;
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

        btnAddNewContact = (Button) findViewById(R.id.btnAddNewContact);
        btnAddNewContact.setOnClickListener(this);

        btnDeleteContact = (Button) findViewById(R.id.btnDeleteContact);
        btnDeleteContact.setOnClickListener(this);

        btnUpdateContact = (Button) findViewById(R.id.btnUpdateContact);
        btnUpdateContact.setOnClickListener(this);

        mNameList = new ArrayList();

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mNameList);

        Log.i("Size of database",address.size()+"");


        for (Address cn : address) {
            mNameList.add( cn.getName() + ", \t       " + cn.getPhoneNumber());
            String log = "Name: " + cn.getName()+ " ,Surname: " + cn.getLastName() + " ,Phone: " + cn.getPhoneNumber() +  " ,Email: " + cn.getEmail()+ " ,Address: " + cn.getAddress();
            // Writing Contacts to log
            Log.i("Address: ", log);
            arrayAdapter.notifyDataSetChanged();

        }
        ListViewAddress.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        currentActiveItemID = 0;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        List<Address> ad = db.getAllAddress();
        int index = ad.get(position).getID();

        currentActiveItemID = (index);
        currentActiveItemNum = position;

        txtVName = (TextView) findViewById(R.id.txtVName);
        txtVSurname =(TextView) findViewById(R.id.txtVSurname);
        txtVEmail = (TextView) findViewById(R.id.txtVEmail);
        txtVCell = (TextView) findViewById(R.id.txtVCell);
        txtVAddress = (TextView) findViewById(R.id.txtVAddress);

        txtVName.setText(""+ad.get(position).getName());
        txtVSurname.setText(""+ad.get(position).getLastName());
        txtVEmail.setText(""+ad.get(position).getEmail());
        txtVCell.setText(""+ad.get(position).getPhoneNumber());
        txtVAddress.setText(""+ad.get(position).getAddress());;


    }
    public void updateTextBoxes(int position){
        List<Address> ad = db.getAllAddress();
        int index = ad.get(position).getID();

        txtVName.setText(""+ad.get(position).getName());
        txtVSurname.setText(""+ad.get(position).getLastName());
        txtVEmail.setText(""+ad.get(position).getEmail());
        txtVCell.setText(""+ad.get(position).getPhoneNumber());
        txtVAddress.setText(""+ad.get(position).getAddress());;
    }
    public void updateListView()
    {
        address = db.getAllAddress();
        mNameList.clear();
        for (Address cn : address) {
            mNameList.add( cn.getName() + ", \t       " + cn.getPhoneNumber());
            String log = "Name: " + cn.getName()+ " ,Surname: " + cn.getLastName() + " ,Phone: " + cn.getPhoneNumber() +  " ,Email: " + cn.getEmail()+ " ,Address: " + cn.getAddress();
            // Writing Contacts to log
            Log.i("Address: ", log);
            arrayAdapter.notifyDataSetChanged();
        }
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        btnAddNewContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent addressIntent = new Intent(AddressActivity.this, AddressAddActivity.class);
                //   storageIntent.putExtra("text", text);
                startActivity(addressIntent);
            }
        });
        btnUpdateContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(currentActiveItemID==0){
                    Toast.makeText(getBaseContext(), "Please select a contact from the address book above",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try{
                        DatasourceDAO dba = new DatasourceDAOImpl(getApplicationContext());

                        /**
                         * CRUD Operations
                         * */
                        // updating Contact
                        if( !(txtVName.getText().toString().equals("")) && !(txtVSurname.getText().toString().equals("")) && !(txtVEmail.getText().toString().equals("")) && !(txtVCell.getText().toString().equals("")) &&!(txtVAddress.getText().toString().equals("")) &&currentActiveItemID!=0  )
                        {
                            dba.updateAddress(currentActiveItemID,new Address(txtVName.getText().toString(), txtVSurname.getText().toString(), txtVEmail.getText().toString(), txtVCell.getText().toString(), txtVAddress.getText().toString() ));
                            Toast.makeText(getBaseContext(), "Address updated",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),"Unsuccessful, Please enter all the fields!",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                    catch(Exception e)
                    {
                        Toast.makeText(getBaseContext(),"Unsuccessful, Address NOT updated!"+e,
                                Toast.LENGTH_SHORT).show();
                    }

                    //db stuff

                    //update list
                    updateListView();
                    //update textboxes
                    updateTextBoxes(currentActiveItemNum);
                }


            }
        });
        btnDeleteContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (currentActiveItemID == 0) {
                    Toast.makeText(getBaseContext(), "Please select a contact from the address book above",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    try {


                        DatasourceDAO dba = new DatasourceDAOImpl(getApplicationContext());
                        dba.deleteAddress(currentActiveItemID + "");
                        Toast.makeText(getBaseContext(), "Address deleted",
                                Toast.LENGTH_SHORT).show();

                    }
                    catch(Exception e)
                    {
                        Toast.makeText(getBaseContext(),"Unsuccessful, Address NOT deleted!"+e,
                                Toast.LENGTH_SHORT).show();
                    }

                    updateListView();
                    updateTextBoxes(currentActiveItemNum);
                }

            }
        });
    }
}
