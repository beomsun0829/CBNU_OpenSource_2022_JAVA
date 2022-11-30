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
    private ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apptheme);

        backbutton = (ImageView) findViewById(R.id.backbutton);
        themeColor = ThemeUtil.modLoad(getApplicationContext());
        ThemeUtil.applyTheme(themeColor);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(apptheme_activity.this, setting_activity.class); //위치지정
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent); //액티비티 이동
            }
        });

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