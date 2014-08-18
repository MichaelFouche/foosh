package com.athome.foosh.foosh;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Michael on 2014-08-18.
 */
public class Storage extends Activity implements View.OnClickListener {
    TextView storageText ;
    Button loadButton;
    Button saveButton;
    private String file = "fooshDoc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        // 11. Add a spinning progress bar (and make sure it's off)
      //  requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
       // setProgressBarIndeterminateVisibility(false);


        setContentView(R.layout.activity_storage);
        storageText = (TextView) findViewById(R.id.storageText);
        loadButton = (Button) findViewById(R.id.loadBtn);
        loadButton.setOnClickListener(this);

        saveButton = (Button) findViewById(R.id.saveBtn);
        saveButton.setOnClickListener(this);

        storageText.setText(this.getIntent().getExtras().getString("text"));
    }



    @Override
    public void onClick(View v) {

        loadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String text = storageText.getText().toString();
                //Toast.makeText(getApplicationContext(), "Text: " +text , Toast.LENGTH_LONG).show();

                try{
                    FileInputStream fin = openFileInput(file);
                    int c;
                    String temp="";
                    while( (c = fin.read()) != -1){
                        temp = temp + Character.toString((char)c);
                    }
                    storageText.setText(temp);
                    Toast.makeText(getBaseContext(),"file read",
                            Toast.LENGTH_SHORT).show();

                }catch(Exception e){
                    Toast.makeText(getBaseContext(),"file NOT read" +e,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String data = storageText.getText().toString();
               // Toast.makeText(getApplicationContext(), "Text: " +data , Toast.LENGTH_LONG).show();

                try {
                    FileOutputStream fOut = openFileOutput(file,MODE_WORLD_READABLE);
                    fOut.write(data.getBytes());
                    fOut.close();
                    Toast.makeText(getBaseContext(),"file saved",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(),"file NOT saved" +e,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
