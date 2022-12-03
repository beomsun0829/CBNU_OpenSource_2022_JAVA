package com.example.practice;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class pontsize_activity extends AppCompatActivity {

    private ImageView backbutton;
    private Button normal_size;
    private Button big_size;
    private TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontsize);


        normal_size = (Button) findViewById(R.id.normal_size);
        big_size = (Button) findViewById(R.id.big_size);
        test= (TextView) findViewById(R.id.test);



        //TextView abc =  ((list_Fragment) getSupportFragmentManager().findFragmentByTag("list_Fragment")).title;
        //list_Fragment tf = (list_Fragment) getSupportFragmentManager().findFragmentById(R.id.title);



        normal_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //itleTextview.setTextSize(5);
            }
        });




    }
}