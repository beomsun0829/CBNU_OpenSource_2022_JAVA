package com.example.practice;

import static com.example.practice.cleanCash.clearAppData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class setting_activity extends AppCompatActivity {

    private TextView clear_cash;
    private TextView apptheme;
    private TextView feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        apptheme = (TextView) findViewById(R.id.apptheme);
        clear_cash = (TextView) findViewById(R.id.clear_cash);
        feedback =(TextView) findViewById(R.id.feedback);


        apptheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(setting_activity.this,apptheme_activity.class); //위치지정
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent); //액티비티 이동
            }
        });
        clear_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //앱 데이터 삭제
                clearAppData(setting_activity.this);
                Toast.makeText(setting_activity.this, "캐시 데이터를 삭제했습니다.", Toast.LENGTH_LONG).show();
            }
        });
        feedback.setOnClickListener(new TextView.OnClickListener() {
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("plain/text");
                String[] address = {"email@address.com"};
                email.putExtra(Intent.EXTRA_EMAIL, address);
                email.putExtra(Intent.EXTRA_SUBJECT, "test");
                email.putExtra(Intent.EXTRA_TEXT, "내용 미리보기 (미리적을 수 있음)");
                startActivity(email);
            }
        });



    }
}