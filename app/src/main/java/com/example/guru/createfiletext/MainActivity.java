package com.example.guru.createfiletext;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    String fileName = "domainweb" + ".txt";//like 2016_01_12.txt
    File gpxfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 23) {
            //do your check here
            isStoragePermissionGranted();
        }

        Button btn=(Button)findViewById(R.id.btncek);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(getApplicationContext(),Cekdata.class);
                startActivity(in);
            }
        });
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("weleh","Permission is granted");
                return true;
            } else {

                Log.v("weleh","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("weleh","Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v("weleh","Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
            try
            {
                File root = new File(Environment.getExternalStorageDirectory()+File.separator+"surveyku", "folderku");
                if (!root.exists())
                {
                    root.mkdirs();
                }
                gpxfile = new File(root, fileName);
                FileWriter writer = new FileWriter(gpxfile,true);
                writer.append("makanan ini enak");
                writer.flush();
                writer.close();
                Toast.makeText(this, "Data has been written to Report File", Toast.LENGTH_SHORT).show();
            }
            catch(IOException e)
            {
                Log.d("weleh", "onCreate: "+e.toString());
            }
        }
    }
}