package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class initiate_activity extends AppCompatActivity {

    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiate);

        //버튼 4개 연결



        //홈 화면 가기
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(initiate_activity.this , home_activity.class); //위치지정
                startActivity(intent); //액티비티 이동
            }
        });


        //설정 가기
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(initiate_activity.this , home_activity.class); //위치지정
                startActivity(intent); //액티비티 이동
            }
        });

        //즐겨찾기 가기
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(initiate_activity.this , home_activity.class); //위치지정
                startActivity(intent); //액티비티 이동
            }
        });

        //검색가기
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(initiate_activity.this , home_activity.class); //위치지정
                startActivity(intent); //액티비티 이동
            }
        });


    }
}