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
import android.widget.ImageView;
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
    private TextView text_tel;


    private ImageView bookmark;

    private ImageView imageView;

    private String dlat;
    private String dlng;
    private String dname;
    String str_name;
    String str_address;
    int check= 0;
    int piccount=0;
    String stri;

    String str_lat;
    String str_lng;

    int aorp =0;
    String straorp;

    int bk =0;
    String strbk;

    String phone;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        Intent intent = getIntent();
        str_name = intent.getStringExtra("str_name"); //스트링을 받음 시설명
        text_name = findViewById(R.id.text_name);
        text_name.setText(str_name); //이름 넣음

        str_address = intent.getStringExtra("str_address"); //스트링을 받음 주소
        text_address = findViewById(R.id.text_address);
        text_address.setText(str_address); //주소 출력

        str_lat = intent.getStringExtra("str_lat"); //스트링을 받음 경도
        str_lng = intent.getStringExtra("str_lng"); //스트링을 받음 위도
        phone= intent.getStringExtra("phone"); //스트링을 받음 위도

        stri = intent.getStringExtra("num"); //스트링을 받음 경도
        piccount = Integer.parseInt(stri);


        straorp = intent.getStringExtra("aorp"); //서치 여부
        aorp=Integer.parseInt(straorp);


        strbk = intent.getStringExtra("bk"); //서치 여부
        bk=Integer.parseInt(strbk);

        imageView = findViewById(R.id.imageView);

        if (aorp==0){
            if(piccount %10==0){
                imageView.setImageResource(R.drawable.a1);
            }else if(piccount %10==1) {
                imageView.setImageResource(R.drawable.a2);
            }
            else if(piccount %10==2) {
                imageView.setImageResource(R.drawable.a3);
            }
            else if(piccount %10==3) {
                imageView.setImageResource(R.drawable.a4);
            }
            else if(piccount %10==4) {
                imageView.setImageResource(R.drawable.a5);
            }
            else if(piccount %10==5) {
                imageView.setImageResource(R.drawable.a6);
            }
            else if(piccount %10==6) {
                imageView.setImageResource(R.drawable.a7);
            }
            else if(piccount %10==7) {
                imageView.setImageResource(R.drawable.a8);
            }
            else if(piccount %10==8) {
                imageView.setImageResource(R.drawable.a9);
            }
            else if(piccount %10==9) {
                imageView.setImageResource(R.drawable.a10);
            }
        }
        else if (aorp == 1){
            if(piccount %10==0){
                imageView.setImageResource(R.drawable.p1);
            }else if(piccount %10==1) {
                imageView.setImageResource(R.drawable.p2);
            }
            else if(piccount %10==2) {
                imageView.setImageResource(R.drawable.p3);
            }
            else if(piccount %10==3) {
                imageView.setImageResource(R.drawable.p4);
            }
            else if(piccount %10==4) {
                imageView.setImageResource(R.drawable.p5);
            }
            else if(piccount %10==5) {
                imageView.setImageResource(R.drawable.p6);
            }
            else if(piccount %10==6) {
                imageView.setImageResource(R.drawable.p7);
            }
            else if(piccount %10==7) {
                imageView.setImageResource(R.drawable.p8);
            }
            else if(piccount %10==8) {
                imageView.setImageResource(R.drawable.p9);
            }
            else if(piccount %10==9) {
                imageView.setImageResource(R.drawable.p10);
            }
        }
        else{
            if(piccount %10==0){
                imageView.setImageResource(R.drawable.a2);
            }else if(piccount %10==1) {
                imageView.setImageResource(R.drawable.a3);
            }
            else if(piccount %10==2) {
                imageView.setImageResource(R.drawable.a4);
            }
            else if(piccount %10==3) {
                imageView.setImageResource(R.drawable.a1);
            }
            else if(piccount %10==4) {
                imageView.setImageResource(R.drawable.a5);
            }
            else if(piccount %10==5) {
                imageView.setImageResource(R.drawable.a6);
            }
            else if(piccount %10==6) {
                imageView.setImageResource(R.drawable.a7);
            }
            else if(piccount %10==7) {
                imageView.setImageResource(R.drawable.a8);
            }
            else if(piccount %10==8) {
                imageView.setImageResource(R.drawable.a9);
            }
            else if(piccount %10==9) {
                imageView.setImageResource(R.drawable.a10);
            }
        }

        text_tel=  findViewById(R.id.text_tel);
        text_tel.setText(phone);


        String str_WfcltId = intent.getStringExtra("str_WfcltId"); //스트링을 받음 위도

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

        bookmark = (ImageView) findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bk==0){
                    check =1-check;
                    if (check==0){
                        bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
//                        String checkstring= Integer.toString(check);
//                        Log.e("check 확인",checkstring);
                    }
                    else {
                        bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24);
//                        String checkstring = Integer.toString(check);
//                        Log.e("check 확인", checkstring);
                    }
                }
            }
        });
    }
    public void onBackPressed(){
        //Log.e("뒤로가기 키 확인","ㅇㅇ 작동");
        if( check== 1){
            Intent intentR = new Intent();
            setResult(RESULT_OK,intentR);
            intentR.putExtra("str1",str_name);
            intentR.putExtra("str2",str_address);
            intentR.putExtra("str3",str_lng);
            intentR.putExtra("str4",str_lat);
        }
        finish();
    }
}