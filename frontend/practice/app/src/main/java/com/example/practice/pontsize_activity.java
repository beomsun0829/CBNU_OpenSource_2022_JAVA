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

        backbutton = (ImageView) findViewById(R.id.backbutton);
        normal_size = (Button) findViewById(R.id.normal_size);
        big_size = (Button) findViewById(R.id.big_size);
        test= (TextView) findViewById(R.id.test);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(pontsize_activity.this, setting_activity.class); //위치지정
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent); //액티비티 이동
            }
        });

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