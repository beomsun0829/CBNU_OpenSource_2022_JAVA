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

public class home_activity extends AppCompatActivity {

    private ListView listview ;
    private com.example.practice.ListViewAdapter adapter;
    private TextView adress;// 왼쪽위 주소버튼
    private ImageView setting; //오른쪽 위 세팅버튼
    private ImageView home; //왼쪽 아래 홈버튼
    private ImageView bookmark; //중앙 아래 북마크버튼
    private ImageView search; //오른쪽 아래 검색버튼

    private SwipeRefreshLayout swipeRefreshLayout;


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
                Intent intent =  new Intent(home_activity.this , info_activity.class); //위치지정
                startActivity(intent); //액티비티 이동
            }
        });
        // 주소 버튼 누르기
        adress = (TextView) findViewById(R.id.adress);
        adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(home_activity.this, location_activity.class); //위치지정
                startActivity(intent); //액티비티 이동
            }
        });

        // 설정 버튼 누르기
        setting = (ImageView) findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(home_activity.this, setting_activity.class); //위치지정
                startActivity(intent); //액티비티 이동
            }
        });
        //북마크 버튼 누르기
        bookmark = (ImageView) findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(home_activity.this,bookmark_activity.class); //위치지정
                startActivity(intent); //액티비티 이동
            }
        });

        //검색 버튼 누르기
        search = (ImageView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(home_activity.this,search_activity.class); //위치지정
                startActivity(intent); //액티비티 이동
            }
        });

        // 홈 버튼 누르기
        home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish(); //현재 액티비티 종료 실행
                overridePendingTransition(0,0);
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent); //현재 액티비티 재실행 실시
                overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
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
