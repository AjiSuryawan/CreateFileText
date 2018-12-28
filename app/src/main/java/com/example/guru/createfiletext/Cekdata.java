package com.example.guru.createfiletext;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.example.guru.createfiletext.DataDomain.DOMAIN_SAYA;

public class Cekdata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cekdata);
        File root = new File(Environment.getExternalStorageDirectory()+File.separator+"surveyku", "folderku");

//Get the text file
        File file = new File(root,"domainweb" + ".txt");

//Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }

        DOMAIN_SAYA=text.toString();

//Find the view by its id
        TextView tv = (TextView)findViewById(R.id.txtdata);

//Set the text
        tv.setText(DOMAIN_SAYA);
    }
}
