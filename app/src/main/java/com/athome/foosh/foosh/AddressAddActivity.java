package com.athome.foosh.foosh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import repository.DatasourceDAO;
import repository.DatasourceDAOImpl;

/**
 * Created by Bob on 2014/08/22.
 */
public class AddressAddActivity extends Activity implements View.OnClickListener {
    TextView txtFname,txtLname, txtEmail, txtCell, txtHAddress;
    Button btSubmit, btHome, btAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        btSubmit =(Button) findViewById(R.id.btnSubmit);
        btSubmit.setOnClickListener(this);

        btHome =(Button) findViewById(R.id.btnHome);
        btHome.setOnClickListener(this);

        btAddress = (Button) findViewById(R.id.btnAddress);
        btAddress.setOnClickListener(this);

        txtFname = (TextView) findViewById(R.id.txtFname);
        txtLname = (TextView) findViewById(R.id.txtLname);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtCell = (TextView) findViewById(R.id.txtCell);
        txtHAddress = (TextView) findViewById(R.id.txtHAddress);
    }
        @Override
    public void onClick(View v) {

        btSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try{


                    DatasourceDAO dba = new DatasourceDAOImpl(getApplicationContext());

                    /**
                     * CRUD Operations
                     * */
                    // Inserting Contact
                    if( !(txtFname.getText().toString().equals("")) && !(txtLname.getText().toString().equals("")) && !(txtEmail.getText().toString().equals("")) && !(txtCell.getText().toString().equals("")) &&!(txtHAddress.getText().toString().equals(""))   )
                    {
                         dba.addAddress(new Address(txtFname.getText().toString(), txtLname.getText().toString(), txtEmail.getText().toString(), txtCell.getText().toString(), txtHAddress.getText().toString() ));
                         Toast.makeText(getBaseContext(),"Address added to the database",
                                Toast.LENGTH_SHORT).show();
                        Intent addressIntent = new Intent(AddressAddActivity.this, AddressActivity.class);
                        startActivity(addressIntent);

                        /*txtFname.setText(null);
                        txtLname.setText(null);
                        txtEmail.setText(null);
                        txtCell.setText(null);
                        txtHAddress.setText(null);*/

                    }
                    else
                    {
                        Toast.makeText(getBaseContext(),"Unsuccessful, Enter all the fields!",
                                Toast.LENGTH_SHORT).show();
                    }

                }
                catch(Exception e)
                {
                    Toast.makeText(getBaseContext(),"Unsuccessful, Address NOT added to the database!"+e,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        btHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent addressIntent = new Intent(AddressAddActivity.this, Homescreen.class);
                //   storageIntent.putExtra("text", text);
                startActivity(addressIntent);
            }
        });

        btAddress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent addressIntent = new Intent(AddressAddActivity.this, AddressActivity.class);
                //   storageIntent.putExtra("text", text);
                startActivity(addressIntent);
            }
        });

        }
}
