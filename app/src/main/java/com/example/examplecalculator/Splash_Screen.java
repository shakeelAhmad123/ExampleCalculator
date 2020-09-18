package com.example.examplecalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.io.File;
import java.util.ArrayList;


public class Splash_Screen extends AppCompatActivity {

Button splash_start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        splash_start = findViewById(R.id.splash_start);


        splash_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               multipermissionsplash();

            }
        });
    }

    public void multipermissionsplash(){

        String[] per={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
        Permissions.check(this, per, null, null, new PermissionHandler() {
            @Override
            public void onGranted() {

                String savePathI = Environment.getExternalStorageDirectory().getPath() + "/MyAppPicture/";
                String folder_mainI = "MyAppPicture";
                File fileI = new File(Environment.getExternalStorageDirectory(), folder_mainI);
                if (!fileI.exists()) {
                    fileI.mkdirs();

                }

                String savePathM = Environment.getExternalStorageDirectory().getPath() + "/MyAppMusic/";
                String folder_mainM = "MyAppMusic";
                File fileM = new File(Environment.getExternalStorageDirectory(), folder_mainM);
                if (!fileM.exists()) {
                    fileM.mkdirs();

                }

                String savePathV = Environment.getExternalStorageDirectory().getPath() + "/MyAppVideos/";
                String folder_mainV = "MyAppVideos";
                File fileV = new File(Environment.getExternalStorageDirectory(), folder_mainV);
                if (!fileV.exists()) {
                    fileV.mkdirs();

                }

                Intent intent = new Intent(Splash_Screen.this,MainActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                super.onDenied(context, deniedPermissions);

                AlertDialog.Builder builder = new AlertDialog.Builder(Splash_Screen.this);
                builder.setIcon(R.drawable.warning);
                builder.setMessage("if you not set password you can not hide your media");
                builder.setTitle("Alert Message");

                builder.setPositiveButton("KEEP", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        multipermissionsplash();

                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(Splash_Screen.this,Calculation_activity.class));

                    }
                });

                builder.show();

            }
        });

    }

}
