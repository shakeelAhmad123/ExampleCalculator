package com.example.examplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.URI;

public class Camera_layout extends AppCompatActivity {


    FloatingActionButton floating_add_camera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_layout);



        floating_add_camera = findViewById(R.id.floating_add_camera);


        floating_add_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }
        });




    }
}
