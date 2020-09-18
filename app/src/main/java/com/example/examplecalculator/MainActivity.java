package com.example.examplecalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.StateSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button seven7_btn,four4_btn,one1_btn,zero0_btn,
            eight8_btn,five5_btn,two2_btn,dot_btn,
            nine9_btn,six6_btn,three3_btn,equal_btn,
            ac_btn,devision_btn,multiply_btn,minus_btn,plus_btn;

    TextView result_tv;
    String sharedPassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        plus_btn.setEnabled(false);
        minus_btn.setEnabled(false);
        multiply_btn.setEnabled(false);
        devision_btn.setEnabled(false);

        sharedPreferences = getSharedPreferences("PASS", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("PASSWORD")) {

            startActivity(new Intent(MainActivity.this, Calculation_activity.class));
            finish();

        }
        else {

            ac_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText("");

                }
            });

            nine9_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText(result_tv.getText() + "9");

                }
            });

            eight8_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText(result_tv.getText() + "8");

                }
            });

            seven7_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText(result_tv.getText() + "7");

                }
            });

            six6_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText(result_tv.getText() + "6");

                }
            });

            five5_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText(result_tv.getText() + "5");

                }
            });

            four4_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText(result_tv.getText() + "4");

                }
            });

            three3_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText(result_tv.getText() + "3");

                }
            });

            two2_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText(result_tv.getText() + "2");

                }
            });

            one1_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText(result_tv.getText() + "1");

                }
            });

            zero0_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText(result_tv.getText() + "0");

                }
            });


            plus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText(result_tv.getText() + "+");

                }
            });

            minus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText(result_tv.getText() + "-");

                }
            });

            devision_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText(result_tv.getText() + "/");

                }
            });

            multiply_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result_tv.setText(result_tv.getText() + "*");

                }
            });


            equal_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (result_tv.getText().toString().isEmpty()) {

                        Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_LONG).show();

                    } else {

                        sharedPassword = result_tv.getText().toString();
                        editor = sharedPreferences.edit();
                        editor.putString("PASSWORD", sharedPassword);

                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setIcon(R.drawable.savedrbl);
                        builder.setTitle("Alert Message");
                        builder.setMessage("Do you want to save this password    : ' "  + sharedPassword +  " '");

                        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                editor.apply();

                                Intent i = new Intent(getApplicationContext(), Calculation_activity.class);
                                startActivity(i);
                                finish();


                            }
                        });
                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                                builder1.setTitle("Alert Dialog");
                                builder1.setMessage("If you not save password you can not hide media");
                                builder1.setIcon(R.drawable.warning);
                                builder1.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                      editor.apply();
                                        Intent i = new Intent(getApplicationContext(), Calculation_activity.class);
                                        startActivity(i);
                                        finish();

                                    }
                                });
                                builder1.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent i = new Intent(getApplicationContext(), Calculation_activity.class);
                                        startActivity(i);
                                        finish();

                                    }
                                });
                                builder1.show();

                            }
                        });
                        builder.show();




                    }
                }
            });

        }
    }

}

