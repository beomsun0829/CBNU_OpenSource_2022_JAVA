package com.example.practice;

import static android.media.AudioTrack.ERROR;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class blindid_activity extends AppCompatActivity {


    //음성인식용
    private TextToSpeech tts;
    TextView textView;
    String result = "";

    SpeechRecognizer mRecognizer;
    final int PERMISSION = 1;
    Intent intent;
    Button ttsBtn;
    Button sttBtn;


    int count=0;
    String str="";
    String name="";
    String birth="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blindid);


        //소리넣기
        SoundPool sound= new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        int soundId = sound.load(this,R.raw.toucheffect,1);

        // 퍼미션 체크
        if ( Build.VERSION.SDK_INT >= 23 ){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET,
                    Manifest.permission.RECORD_AUDIO},PERMISSION);
        }

        // xml의 버튼과 텍스트 뷰 연결
        textView = (TextView)findViewById(R.id.sttResult);

        //tts ///////////////////////////////////////////////////
        ttsBtn = (Button) findViewById(R.id.ttsbutton);

        tts= new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!= ERROR){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });
        // RecognizerIntent 객체 생성
        intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR");


        ttsBtn.setOnClickListener(v -> {
            if (count==0){
                tts.speak("화면중앙을 누르고 이름을 이야기해 주세요. 이름 입력이 끝났다면 화면중앙을 다시한번 눌러주세요",TextToSpeech.QUEUE_FLUSH,null);
                //tts.speak("시장2다시 1",TextToSpeech.QUEUE_FLUSH,null);
                count+=1;


            }
            else if(count==1) {
                sound.play(soundId,1f,1f,0,0,1f);
                mRecognizer=SpeechRecognizer.createSpeechRecognizer(this);
                mRecognizer.setRecognitionListener(listener);
                mRecognizer.startListening(intent);
                count+=1;
                str =Integer.toString(count);

            }
            else if(count==2) {
                name=textView.getText().toString();
                //tts.speak("시장 2다시2",TextToSpeech.QUEUE_FLUSH,null);
                tts.speak("화면중앙을 누르고 생년월일을 이야기해 주세요. 생년월일 입력이 끝났다면 화면중앙을 계속 눌러보며 서비스를 이용해 주세요",TextToSpeech.QUEUE_FLUSH,null);
                count+=1;
            }
            else if(count==3) {
                sound.play(soundId,1f,1f,0,0,1f);
                mRecognizer=SpeechRecognizer.createSpeechRecognizer(this);
                mRecognizer.setRecognitionListener(listener);
                mRecognizer.startListening(intent);
                count+=1;
                str =Integer.toString(count);

                
            }
            else{
                birth=textView.getText().toString();
                Intent intent2 = new Intent(blindid_activity.this, blindmode_activity.class); //위치지정
                sound.play(soundId,1f,1f,0,0,1f);
                intent.putExtra("str_name",name); //이름 쏴주기 반대편으로
                intent.putExtra("str_birth",birth); //생년월일 쏴주기 반대편으로
                //intent.putExtra("str_lng",cur_lng); //경도 쏴주기 반대편으로
                //intent.putExtra("str_lat",cur_lat); //위도 쏴주기 반대편으로
//                Log.e("정상적인 연결 : ","name = " +name);
//                Log.e("정상적인 연결 : ","birth = " +birth);
                startActivity(intent2); //액티비티 이동
            }
        });

        tts= new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!= ERROR){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

    }

    private RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {
            Toast.makeText(getApplicationContext(),"음성인식을 시작합니다.",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBeginningOfSpeech() {}

        @Override
        public void onRmsChanged(float rmsdB) {}

        @Override
        public void onBufferReceived(byte[] buffer) {}

        @Override
        public void onEndOfSpeech() {}

        @Override
        public void onError(int error) {
            String message;

            switch (error) {
                case SpeechRecognizer.ERROR_AUDIO:
                    message = "오디오 에러";
                    break;
                case SpeechRecognizer.ERROR_CLIENT:
                    message = "클라이언트 에러";
                    break;
                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    message = "퍼미션 없음";
                    break;
                case SpeechRecognizer.ERROR_NETWORK:
                    message = "네트워크 에러";
                    break;
                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                    message = "네트웍 타임아웃";
                    break;
                case SpeechRecognizer.ERROR_NO_MATCH:
                    message = "찾을 수 없음";
                    count-=1;

                    str =Integer.toString(count);
                    Log.e("정상적인 연결 : ","count = " +str);
                    break;
                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    message = "RECOGNIZER가 바쁨";
                    break;
                case SpeechRecognizer.ERROR_SERVER:
                    message = "서버가 이상함";
                    break;
                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    message = "말하는 시간초과";
                    break;
                default:
                    message = "알 수 없는 오류임";
                    break;
            }

            Toast.makeText(getApplicationContext(), "에러가 발생하였습니다. : " + message,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResults(Bundle results) {
            // 말을 하면 ArrayList에 단어를 넣고 textView에 단어를 이어줍니다.
            ArrayList<String> matches =
                    results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            for(int i = 0; i < matches.size() ; i++){
                textView.setText(matches.get(i));
            }
        }

        @Override
        public void onPartialResults(Bundle partialResults) {}

        @Override
        public void onEvent(int eventType, Bundle params) {}
    };



}