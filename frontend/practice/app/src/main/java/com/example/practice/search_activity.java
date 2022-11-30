package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class search_activity extends AppCompatActivity {




    private ImageView search; //검색버튼




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        EditText editText = (EditText)findViewById(R.id.edit_text);



        //검색 버튼 누르기
        search = (ImageView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search_activity.this, search_result.class); //위치지정
                String search_text = editText.getText().toString() ;
                intent.putExtra("text",search_text); //str1 쏴주기 반대편으로
                startActivity(intent); //액티비티 이동
            }
        });



    }
}