package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class setting_activity extends AppCompatActivity {

    private ImageView backbutton;
    private TextView pontsize;
    private TextView apptheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        backbutton = (ImageView) findViewById(R.id.backbutton);
        pontsize = (TextView) findViewById(R.id.pontsize);
        apptheme = (TextView) findViewById(R.id.apptheme);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(setting_activity.this, home_activity.class); //위치지정
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent); //액티비티 이동
            }
        });

        pontsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(setting_activity.this,pontsize_activity.class); //위치지정
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent); //액티비티 이동
            }
        });
        apptheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(setting_activity.this,apptheme_activity.class); //위치지정
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent); //액티비티 이동
            }
        });



    }
}