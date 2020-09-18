package com.example.examplecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Calculation_activity extends AppCompatActivity {

    Button seven7_btn,four4_btn,one1_btn,zero0_btn,
            eight8_btn,five5_btn,two2_btn,
            nine9_btn,six6_btn,three3_btn,equal_btn,
            ac_btn,devision_btn,multiply_btn,minus_btn,plus_btn;

    TextView result_tv;
    float result1,result2;
    boolean plus,minus,multiply,devision;
    String channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_activity);

        result_tv = findViewById(R.id.result_tv);
        seven7_btn = findViewById(R.id.seven7_btn);
        four4_btn = findViewById(R.id.four4_btn);
        one1_btn = findViewById(R.id.one1_btn);
        zero0_btn = findViewById(R.id.zero0_btn);
        eight8_btn = findViewById(R.id.eight8_btn);
        five5_btn = findViewById(R.id.five5_btn);
        two2_btn = findViewById(R.id.two2_btn);
        nine9_btn = findViewById(R.id.nine9_btn);
        six6_btn = findViewById(R.id.six6_btn);
        three3_btn = findViewById(R.id.three3_btn);
        equal_btn = findViewById(R.id.equal_btn);
        ac_btn = findViewById(R.id.ac_btn);
        devision_btn = findViewById(R.id.devision_btn);
        multiply_btn = findViewById(R.id.multiply_btn);
        minus_btn = findViewById(R.id.minus_btn);
        plus_btn = findViewById(R.id.plus_btn);

        SharedPreferences shared = getSharedPreferences("PASS", MODE_PRIVATE);
         channel = (shared.getString("PASSWORD", ""));

        one1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result_tv.setText(result_tv.getText()+"1");
            }
        });

        two2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result_tv.setText(result_tv.getText()+"2");
            }
        });

        three3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result_tv.setText(result_tv.getText()+"3");
            }
        });

        four4_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result_tv.setText(result_tv.getText()+"4");
            }
        });

        five5_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result_tv.setText(result_tv.getText()+"5");
            }
        });

        six6_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result_tv.setText(result_tv.getText()+"6");
            }
        });

        seven7_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result_tv.setText(result_tv.getText()+"7");
            }
        });

        eight8_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result_tv.setText(result_tv.getText()+"8");
            }
        });

        nine9_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result_tv.setText(result_tv.getText()+"9");
            }
        });

        zero0_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result_tv.setText(result_tv.getText()+"0");
            }
        });

        equal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (result_tv.getText().toString().isEmpty()){
                }

                else if (channel.matches(result_tv.getText().toString())) {
                    Intent intent = new Intent(Calculation_activity.this,Media_activity.class);
                    startActivity(intent);
                    result_tv.setText("");
                }

                else {
                    result2 = Integer.parseInt(result_tv.getText()+"");

                    if (multiply==true){
                        result_tv.setText(result1*result2+"");
                        multiply=false;
                    }

                    if (devision==true){
                        result_tv.setText(result1/result2+"");
                        devision=false;
                    }

                    if (minus==true){
                        result_tv.setText(result1-result2+"");
                        minus=false;
                    }

                    if (plus==true){
                        result_tv.setText(result1+result2+"");
                        plus=false;
                    }
                }
                }

        });

        ac_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              result_tv.setText("");
            }
        });

        multiply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (result_tv.getText().toString().isEmpty()){
                }
                else {
                    result1 = Float.parseFloat(result_tv.getText()+"");
                    multiply = true;
                    result_tv.setText(null);
                }
            }
        });

        minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (result_tv.getText().toString().isEmpty()){
                }
                else {
                    result1 = Float.parseFloat(result_tv.getText()+"");
                    minus = true;
                    result_tv.setText(null);
                }
            }
        });

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (result_tv.getText().toString().isEmpty()){
                }
                else {
                    result1 = Float.parseFloat(result_tv.getText()+"");
                        plus = true;
                        result_tv.setText(null);
                }
            }
        });

        devision_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (result_tv.getText().toString().isEmpty()){
                }
                else {
                    result1 = Float.parseFloat(result_tv.getText()+"");
                    devision = true;
                    result_tv.setText(null);
                }
            }
        });

        }
}
