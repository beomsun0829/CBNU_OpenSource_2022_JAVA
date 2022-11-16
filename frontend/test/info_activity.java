package com.example.naverai;

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

public class info_activity extends AppCompatActivity {

    private Button info;
    private Button info2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        info = findViewById(R.id.info_button);
        info2 = findViewById(R.id.map_button);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_home = new Intent(info_activity.this, MainActivity.class);
                startActivity(intent_home);
            }
        });
        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_map = new Intent(info_activity.this,HomeActivity.class);
                startActivity(intent_map);
            }
        });

    }




}