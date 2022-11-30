package com.example.practice;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class mypage_activity extends AppCompatActivity {
    private TextView name;
    private TextView birth;
    private TextView disable_kinds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        name = (TextView) findViewById(R.id.name);
        birth = (TextView) findViewById(R.id.birth);
        disable_kinds = (TextView) findViewById(R.id.disable_kinds);

        Intent intent = getIntent();

        name.setText(intent.getStringExtra("이름"));
        birth.setText(intent.getStringExtra("년도")+ "년 " +intent.getStringExtra("월") +"월 "+intent.getStringExtra("일") +"일"  );
        disable_kinds.setText(intent.getStringExtra("장애종류"));




    }
}