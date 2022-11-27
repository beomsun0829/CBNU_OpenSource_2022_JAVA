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

        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");  //(제목 부분, 이미지, 내용)
        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");
        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");
        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");
        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");
        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");
        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");
        adapter.addItem("청주 장애인 복지관", R.drawable.listimage, "충청북도 청주시 개신동 543-2");


        adapter.notifyDataSetChanged(); //어댑터의 변경을 알림.

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(home_activity.this, info_activity.class); //위치지정
                startActivity(intent); //액티비티 이동
            }
        });
        // 주소 버튼 누르기
        adress = (TextView) findViewById(R.id.adress);
        adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_activity.this, data_activity.class); //위치지정
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
