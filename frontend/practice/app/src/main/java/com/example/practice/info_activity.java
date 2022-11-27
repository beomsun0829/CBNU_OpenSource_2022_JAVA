package com.example.practice;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.speech.RecognitionListener;
import static android.media.AudioTrack.ERROR;
import android.widget.Toast;
import java.util.ArrayList;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class info_activity extends AppCompatActivity {


    private Button map_button;
    private Button facilities_button;

    private TextView text_name;
    private TextView text_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        String str1 = intent.getStringExtra("str1"); //스트링을 받음 시설명
        text_name = findViewById(R.id.text_name);
        text_name.setText(str1); //받아온 str1 넣음

        String str2 = intent.getStringExtra("str2"); //스트링을 받음 주소
        text_address = findViewById(R.id.text_address);
        text_address.setText(str2); //받아온 str2 넣음

        String str3 = intent.getStringExtra("str3"); //스트링을 받음 경도
        String str4 = intent.getStringExtra("str4"); //스트링을 받음 위도




        map_button= findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_map = new Intent(info_activity.this,map_activity.class);
                startActivity(intent_map);
            }
        });

        facilities_button= findViewById(R.id.facilities_button);
        facilities_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_map = new Intent(info_activity.this,facilities_activity.class);
                startActivity(intent_map);
            }
        });










    }

}