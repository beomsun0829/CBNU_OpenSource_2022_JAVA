package com.example.practice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.practice.ui.login.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home_activity extends AppCompatActivity {

    private ListView listview;
    private com.example.practice.ListViewAdapter adapter;

    int bookcheck=0;
    String strbookcheck;

    private TextView adress;// 왼쪽위 주소버튼
    private ImageView setting; //오른쪽 위 세팅버튼
    private ImageView mypage; //중앙 아래 마이페이지버튼
    private ImageView bookmark; //왼쪽 아래 북마크버튼
    private ImageView search; //오른쪽 아래 검색버튼
    private SwipeRefreshLayout swipeRefreshLayout;
    public static int login_active = 1;

    Call<List<data_model>> call;
    String name = "";
    String fadress = "";
    String[] namelist = new String[1000];
    String[] fadresslist = new String[1000];
    String[] Lnglist = new String[1000]; //경도
    String[] Latlist = new String[1000]; //위도
    String[] wfcltId = new String[1000]; // 시설코드

    String[] bookmarkname = new String[1000];
    String[] bookmarkadress = new String[1000];
    String[] bookmarkLng = new String[1000];
    String[] bookmarkLat = new String[1000];
    String[] presentnum= new String[1000];
    int i=0;
    String stri;

    String cur_lat;
    String cur_lng;
    public static final int REQUEST_CODE = 100;
    String str_name ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //값 받아오기
        Intent secondIntent = getIntent();

        str_name= secondIntent.getStringExtra("이름");

        // Adapter 생성
        adapter = new com.example.practice.ListViewAdapter();

        // 리스트뷰 참조 및 Adapter 달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        //listview.setOnItemClickListener(listener);


        // 사용자 위도, 경도 받기
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(home_activity.this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else {
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

        for (int i=0; i<1000;i++){
            if(i %10==0){presentnum[i]="0507-1337-2701";}
            else if(i %10==1) {presentnum[i]="043-230-6700";}
            else if(i %10==2) {presentnum[i]="043-295-2505";}
            else if(i %10==3) {presentnum[i]="043-264-1616";}
            else if(i %10==4) {presentnum[i]="043-267-5835";}
            else if(i %10==5) {presentnum[i]="043-201-0001";}
            else if(i %10==6) {presentnum[i]="043-236-0358";}
            else if(i %10==7) {presentnum[i]="043-223-9428";}
            else if(i %10==8) {presentnum[i]="043-716-2163";}
            else if(i %10==9) {presentnum[i]="043-275-7411";}
        }


        call = retrofit_client.getApiService().get_Lat_Lng(cur_lat,cur_lng); // interface get함수 가져오기
        call.enqueue(new Callback<List<data_model>>(){
            //콜백 받는 부분
            @Override
            public void onResponse(Call<List<data_model>> call, Response<List<data_model>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<data_model> result = response.body() ;

                int i =0;
                for (data_model data_model : result) {
                    namelist[i] = data_model.getFaclNm();
                    fadresslist[i]= data_model.getLcMnad();
                    Lnglist[i] = data_model.getFaclLng();
                    Latlist[i]= data_model.getFaclLat();


                    if(i %10==0){
                        adapter.addItem(namelist[i], R.drawable.a1, fadresslist[i]);
                    }else if(i %10==1) {
                        adapter.addItem(namelist[i], R.drawable.a2, fadresslist[i]);
                    }
                    else if(i %10==2) {
                        adapter.addItem(namelist[i], R.drawable.a3, fadresslist[i]);
                    }
                    else if(i %10==3) {
                        adapter.addItem(namelist[i], R.drawable.a4, fadresslist[i]);
                    }
                    else if(i %10==4) {
                        adapter.addItem(namelist[i], R.drawable.a5, fadresslist[i]);
                    }
                    else if(i %10==5) {
                        adapter.addItem(namelist[i], R.drawable.a6, fadresslist[i]);
                    }
                    else if(i %10==6) {
                        adapter.addItem(namelist[i], R.drawable.a7, fadresslist[i]);
                    }
                    else if(i %10==7) {
                        adapter.addItem(namelist[i], R.drawable.a8, fadresslist[i]);
                    }
                    else if(i %10==8) {
                        adapter.addItem(namelist[i], R.drawable.a9, fadresslist[i]);
                    }
                    else if(i %10==9) {
                        adapter.addItem(namelist[i], R.drawable.a10, fadresslist[i]);
                    }

//                    Log.e("정상적인 연결 : ", namelist[i]);
//                    Log.e("정상적인 연결 : ", fadresslist[i]);
                    //adapter.addItem(namelist[i], R.drawable.listimage, fadresslist[i]);

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



//        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");  //(제목 부분, 이미지, 내용)
//        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");
//        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");
//        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");
//        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");
//        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");
//        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");
//        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");
//        adapter.notifyDataSetChanged(); //어댑터의 변경을 알림.


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(home_activity.this, info_activity.class); //위치지정
                intent.putExtra("str_name", namelist[i]); //str1 쏴주기 반대편으로
                intent.putExtra("str_address", fadresslist[i]); //str2 쏴주기 반대편으로
                intent.putExtra("str_lng", Lnglist[i]); //str3 쏴주기 반대편으로
                intent.putExtra("str_lat", Latlist[i]); //str4 쏴주기 반대편으로
                intent.putExtra("str_WfcltId", wfcltId[i]); //시설 코드 쏴주기 반대편으로
                stri= Integer.toString(i);
                intent.putExtra("num",stri ); // 사진 순서
                intent.putExtra("aorp","0" );
                intent.putExtra("bk","0" );
                intent.putExtra("phone",presentnum[i] );


                startActivityForResult(intent, REQUEST_CODE); //액티비티 이동
            }
        });
        // 주소 버튼 누르기
        adress = (TextView) findViewById(R.id.adress);
        adress.setText(str_name);

        adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_activity.this, location_activity.class); //위치지정
                startActivity(intent); //액티비티 이동
            }
        });

        // 설정 버튼 누르기
        setting = (ImageView) findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_activity.this, setting_activity.class); //위치지정

                startActivity(intent); //액티비티 이동
            }
        });
        //북마크 버튼 누르기
        bookmark = (ImageView) findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_activity.this, bookmark_activity.class); //위치지정


                intent.putExtra("str_name", bookmarkname); //str1 쏴주기 반대편으로
                intent.putExtra("str_address", bookmarkadress); //str2 쏴주기 반대편으로
                strbookcheck= Integer.toString(bookcheck);
                intent.putExtra("check", strbookcheck);
                intent.putExtra("lng", bookmarkLng);
                intent.putExtra("lat", bookmarkLat);

                startActivity(intent); //액티비티 이동
            }
        });

        //검색 버튼 누르기
        search = (ImageView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_activity.this, search_activity.class); //위치지정
                intent.putExtra("str_lng", cur_lng); //경도 쏴주기 반대편으로
                intent.putExtra("str_lat", cur_lat); //위도 쏴주기 반대편으로


                startActivity(intent); //액티비티 이동
            }
        });


        // 마이 페이지 버튼 누르기
        mypage = (ImageView) findViewById(R.id.mypage);
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login_active == 0) {
                    Intent intent = new Intent(home_activity.this, LoginActivity.class); //위치지정
                    startActivity(intent); //액티비티 이동
                } else if (login_active == 1) {
                    Intent intent = new Intent(home_activity.this, mypage_activity.class); //위치지정
                    intent.putExtra("이름", secondIntent.getStringExtra("이름"));
                    intent.putExtra("년도", secondIntent.getStringExtra("년도"));
                    intent.putExtra("월", secondIntent.getStringExtra("월"));
                    intent.putExtra("일", secondIntent.getStringExtra("일"));
                    intent.putExtra("장애종류", secondIntent.getStringExtra("장애종류"));
                    startActivity(intent); //액티비티 이동
                }
            }
        });
        swipeRefreshLayout = findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finish();//인텐트 종료
                overridePendingTransition(0, 0);//인텐트 효과 없애기
                Intent intent = getIntent(); //인텐트
                startActivity(intent); //액티비티 열기
                overridePendingTransition(0, 0);//인텐트 효과 없애기
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            String provider = location.getProvider();
            cur_lat = String.valueOf(location.getLatitude());
            cur_lng = String.valueOf(location.getLongitude());
            Log.e("lat", cur_lat);
            Log.e("lng", cur_lng);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        public void onProviderEnabled(String provider) {

        }

        public void onProviderDisabled(String provider) {

        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode,data);
        if (requestCode  == REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                return;
            }
            else{
                bookmarkname[i] = data.getExtras().getString("str1");
                bookmarkadress[i] =data.getExtras().getString("str2");
                bookmarkLng [i]= data.getExtras().getString("str3");
                bookmarkLat [i]= data.getExtras().getString("str4");

                bookcheck+=1;
//            Log.e("이름데이터 도착 = ", bookmarkname[i]);
//            Log.e("주소데이터 도착 = ", bookmarkadress[i]);
                i++;

            }

        }
    }
}

