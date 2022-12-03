package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class apptheme_activity extends AppCompatActivity {

    private static final String TAG ="apptheme_activity";
    Button mod_btn;
    String themeColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apptheme);


        themeColor = ThemeUtil.modLoad(getApplicationContext());
        ThemeUtil.applyTheme(themeColor);


        mod_btn = findViewById(R.id.mod_btn);
        mod_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(getApplicationContext(), ModDialog.class); //위치지정
                startActivity(intent); //액티비티 이동
            }
        });

    }
}