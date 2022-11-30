package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class inputdata_activity extends AppCompatActivity {
    private Button button;
    private EditText name;
    private EditText year;
    private EditText month;
    private EditText day;
    private EditText disable_kinds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata);

        name = (EditText)findViewById(R.id.name);
        year = (EditText)findViewById(R.id.year);
        month = (EditText)findViewById(R.id.month);
        day = (EditText)findViewById(R.id.day);
        disable_kinds = (EditText)findViewById(R.id.disable_kinds);


        // 다음 버튼 누르기
        button = (Button) findViewById(R.id.next_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inputdata_activity.this, home_activity.class); //위치지정
                intent.putExtra("이름", name.getText().toString());
                intent.putExtra("년도", year.getText().toString());
                intent.putExtra("월", month.getText().toString());
                intent.putExtra("일", day.getText().toString());
                intent.putExtra("장애종류", disable_kinds.getText().toString());
                startActivity(intent); //액티비티 이동
            }
        });

    }
}