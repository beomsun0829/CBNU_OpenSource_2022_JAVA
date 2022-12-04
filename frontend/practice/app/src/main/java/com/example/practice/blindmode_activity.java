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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class blindmode_activity extends AppCompatActivity {
    //음성인식용
    private TextToSpeech tts;
    TextView textView;
    String result = "";

    SpeechRecognizer mRecognizer;
    final int PERMISSION = 1;
    Intent intent;
    LinearLayout ttsBtn;
    Button sttBtn;

    TextView ttsbutton_text;

    int count=0;

    private ListView listview;
    private com.example.practice.ListViewAdapter adapter;
    Call<List<data_model>> call;


    String[] namelist=new String[1000];
    String[] fadresslist=new String[1000];
    String[] Lnglist = new String[1000]; //경도
    String[] Latlist = new String[1000]; //위도

    String cur_lat;
    String cur_lng;
    String str_name="";

    int recall = 0;
    String strecall = "";
    String RESULT="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blindmode);

        // 사용자 위도, 경도 받기
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( blindmode_activity.this, new String[] {
                    android.Manifest.permission.ACCESS_FINE_LOCATION}, 0 );
        }
        else {
            Location loc_Current = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (loc_Current != null) {
                cur_lat = (String.valueOf(loc_Current.getLatitude()));
                cur_lng = (String.valueOf(loc_Current.getLongitude()));
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000,
                    1,
                    gpsLocationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000,
                    1,
                    gpsLocationListener);
        }

        ttsbutton_text= (TextView) findViewById(R.id.ttsbutton_text);

        // Adapter 생성
        adapter = new ListViewAdapter();
        // 리스트뷰 참조 및 Adapter 달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);


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
        ttsBtn = (LinearLayout) findViewById(R.id.ttsbutton);

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
                    tts.speak("화면중앙을 누르고 검색하고 싶은 키워드를 이야기해주세요.입력이 끝나면 화면중앙을 눌러주세요",TextToSpeech.QUEUE_FLUSH,null);
                //tts.speak("지역",TextToSpeech.QUEUE_FLUSH,null);

                ttsbutton_text.setText("화면중앙을 누르고"+"\n"+"검색하고 싶은 키워드를"+"\n"+"이야기해주세요."+"\n\n"+"입력이 끝나면"+"\n"+"화면중앙을 눌러주세요.");
                ttsbutton_text.setTextSize(40);

                count+=1;
            }
            else if(count==1) {
                sound.play(soundId,1f,1f,0,0,1f);
                mRecognizer=SpeechRecognizer.createSpeechRecognizer(this);
                mRecognizer.setRecognitionListener(listener);
                mRecognizer.startListening(intent);
                count+=1;
            }
            else if (count==2){
                str_name=textView.getText().toString();
                tts.speak("화면 중앙을 누르고 알고싶은 장소의 갯수를 다섯 같은 숫자로 말해주세요. 입력이 끝나면 화면 중앙을 눌러주세요",TextToSpeech.QUEUE_FLUSH,null);
                ttsbutton_text.setText("화면중앙을 누르고"+"\n"+"알고싶은 장소의 갯수를"+"\n"+"숫자로 말해주세요."+"\n\n"+"입력이 끝나면"+"\n"+"화면중앙을 눌러주세요.");
                ttsbutton_text.setTextSize(40);
                //tts.speak("횟수",TextToSpeech.QUEUE_FLUSH,null);
                count+=1;

            }
            else if(count==3) {
                sound.play(soundId,1f,1f,0,0,1f);
                mRecognizer=SpeechRecognizer.createSpeechRecognizer(this);
                mRecognizer.setRecognitionListener(listener);
                mRecognizer.startListening(intent);
                count+=1;
            }
            else if(count==4) {
                if(str_name==""){
                    str_name="충북";
                }
                Log.e("str_name = ", str_name);
                strecall=textView.getText().toString();
                recall=Integer.parseInt(strecall);
                if(strecall==""){
                    recall=1;
                }
                //Log.e("strecall = ", strecall);


                //Log.e("strecall = ", strecall);

                sound.play(soundId,1f,1f,0,0,1f);

                count+=1;

                call = retrofit_client.getApiService().get_Nm_Lat_Lng(str_name,cur_lat,cur_lng); // interface get함수 가져오기
                call.enqueue(new Callback<List<data_model>>(){//콜백 받는 부분
                    @Override
                    public void onResponse(Call<List<data_model>> call, Response<List<data_model>> response) {
//
                        Log.e("str_name 3", str_name);
                        Log.e("cur_lat 3", cur_lat);
                        Log.e("cur_lng 3", cur_lng);
                        if (!response.isSuccessful()) {
//                    Log.e("비정상적인 연결 : ", "Code : " + response.code());
                            return;
                        }
                        //Log.e("URL : ", call.toString());
                        List<data_model> result = response.body() ;
//                Log.e("정상적인 연결 : ", result.toString());

                        String content = "";
                        int i =0;
                        for (data_model data_model : result) {
                            content += "NAME: " + data_model.getFaclNm() + "\n";
                            content += "TYPE CODE: " + data_model.getFaclTyCd() + "\n";
                            content += "ADDRESS: " + data_model.getLcMnad() + "\n\n";
                            namelist[i] = data_model.getFaclNm();
                            fadresslist[i]= data_model.getLcMnad();
//                    Log.e("정상적인 연결 : ", namelist[i]);
//                    Log.e("정상적인 연결 : ", fadresslist[i]);
                            adapter.addItem(namelist[i], R.drawable.listimage, fadresslist[i]);
                            i++;
                        }
                        adapter.notifyDataSetChanged();
                    }
                    //listview.setOnItemClickListener(listener);
                    @Override
                    public void onFailure(Call<List<data_model>> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
                    }
                });
                tts.speak(" 화면 중앙을 누르시면 검색하신 지역의 장애시설 정보를 현재위치에서 가까운 순서대로 불러드리겠 습니다",TextToSpeech.QUEUE_FLUSH,null);
                ttsbutton_text.setText("화면중앙을 누르시면"+"\n"+"검색하신 지역의"+"\n"+"장애시설 정보를"+"\n"+"현재위치에서"+"\n"+"가까운 순서대로"+"\n"+"불러드리겠습니다.");
                ttsbutton_text.setTextSize(40);
            }
            else {
                sound.play(soundId,1f,1f,0,0,1f);

                for ( int i=0; i<recall;i++){
                    RESULT+="장소이름은."+namelist[i]+".주소는"+fadresslist[i]+". 입니다.";

                }

                RESULT+="더 검색하고 싶으시면 화면 중앙을 눌러주세요";
                tts.speak(RESULT,TextToSpeech.QUEUE_FLUSH,null);
                ttsbutton_text.setText("더 검색하고 싶으시면 "+"\n"+"화면 중앙을 눌러주세요.");
                ttsbutton_text.setTextSize(40);
                count=0;
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


    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            String provider = location.getProvider();
            cur_lat = String.valueOf(location.getLatitude());
            cur_lng = String.valueOf(location.getLongitude());
//            Log.e("lat", cur_lat);
//            Log.e("lng", cur_lng);
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {

        } public void onProviderEnabled(String provider) {

        } public void onProviderDisabled(String provider) {

        }
    };
}