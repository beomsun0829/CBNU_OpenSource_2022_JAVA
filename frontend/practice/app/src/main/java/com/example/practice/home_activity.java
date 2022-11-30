package com.example.practice;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.practice.ui.login.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home_activity extends AppCompatActivity {

    private ListView listview;
    private com.example.practice.ListViewAdapter adapter;

    private TextView adress;// 왼쪽위 주소버튼
    private ImageView setting; //오른쪽 위 세팅버튼
    private ImageView mypage; //중앙 아래 마이페이지버튼
    private ImageView bookmark; //왼쪽 아래 북마크버튼
    private ImageView search; //오른쪽 아래 검색버튼
    private SwipeRefreshLayout swipeRefreshLayout;
    public static int login_active = 0;


    Call<List<data_model>> call;
    String name = "";
    String fadress = "";
    String[] namelist=new String[20];
    String[] fadresslist=new String[20];
    String[] Lnglist = new String[20]; //경도
    String[] Latlist = new String[20]; //위도



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Adapter 생성
        adapter = new com.example.practice.ListViewAdapter();

        // 리스트뷰 참조 및 Adapter 달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        //listview.setOnItemClickListener(listener);

        call = retrofit_client.getApiService().test_api_get("서울"); // interface get함수 가져오기
        call.enqueue(new Callback<List<data_model>>(){
            //콜백 받는 부분
            @Override
            public void onResponse(Call<List<data_model>> call, Response<List<data_model>> response) {
//                Log.e("error 3", "error 3");
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
                    Lnglist[i] = data_model.getFaclLng();
                    Latlist[i]= data_model.getFaclLat();
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
                intent.putExtra("str_name",namelist[i]); //str1 쏴주기 반대편으로
                intent.putExtra("str_address",fadresslist[i]); //str2 쏴주기 반대편으로
                intent.putExtra("str_lng",Lnglist[i]); //str3 쏴주기 반대편으로
                intent.putExtra("str_lat",Latlist[i]); //str4 쏴주기 반대편으로
                startActivity(intent); //액티비티 이동
            }
        });
        // 주소 버튼 누르기
        adress = (TextView) findViewById(R.id.adress);
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
                startActivity(intent); //액티비티 이동
            }
        });

        //검색 버튼 누르기
        search = (ImageView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_activity.this, search_activity.class); //위치지정
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
                }

                else if (login_active==1){
                    Intent intent = new Intent(home_activity.this, mypage_activity.class); //위치지정
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
}
