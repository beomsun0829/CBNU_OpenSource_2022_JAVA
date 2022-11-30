package com.example.practice;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.speech.RecognitionListener;
import static android.media.AudioTrack.ERROR;
import android.widget.Toast;
import java.util.ArrayList;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class info_activity extends AppCompatActivity {


    private Button map_button;
    private Button facilities_button;

    private TextView text_name;
    private TextView text_address;

    private String dlat;
    private String dlng;
    private String dname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        String str_name = intent.getStringExtra("str_name"); //스트링을 받음 시설명
        text_name = findViewById(R.id.text_name);
        text_name.setText(str_name); //이름 넣음

        String str_address = intent.getStringExtra("str_address"); //스트링을 받음 주소
        text_address = findViewById(R.id.text_address);
        text_address.setText(str_address); //주소 출력

        String str_lat = intent.getStringExtra("str_lat"); //스트링을 받음 경도
        String str_lng = intent.getStringExtra("str_lng"); //스트링을 받음 위도


        map_button= findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("**********lat*********",str_lat);
                Log.e("**********lng*********",str_lng);

                dlat = str_lat;            //위도
                dlng = str_lng;             //경도
                dname = str_name;           //이름

                String url = "nmap://route/public?dlat=" +
                        dlat +
                        "&dlng=" +
                        dlng +
                        "&dname=" +
                        dname +
                        "&appname=com.example.practice";

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addCategory(Intent.CATEGORY_BROWSABLE);

                List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

                startActivity(intent);

//                if (list == null || list.isEmpty()) {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.nhn.android.nmap")));
//                } else {
//                    startActivity(intent);
//                }


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