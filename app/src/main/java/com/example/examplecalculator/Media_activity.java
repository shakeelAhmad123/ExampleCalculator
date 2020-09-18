package com.example.examplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Media_activity extends AppCompatActivity {

    ImageButton gallery_btn,camera_btn,video_btn,music_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_activity);


        gallery_btn = findViewById(R.id.gallery_btn);
        camera_btn = findViewById(R.id.camera_btn);
        video_btn = findViewById(R.id.video_btn);
        music_btn = findViewById(R.id.music_btn);


        gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Media_activity.this,Gallery_layout.class);
                startActivity(intent);

            }
        });

        camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Media_activity.this,Camera_layout.class);
                startActivity(intent);


            }
        });

        video_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Media_activity.this,Video_layout.class);
                startActivity(intent);


            }
        });

        music_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Media_activity.this,Music_layout.class);
                startActivity(intent);


            }
        });

    }
}
