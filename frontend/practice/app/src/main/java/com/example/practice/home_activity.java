package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.practice.list_Fragment;

public class home_activity extends AppCompatActivity {
    private list_Fragment list_fragment;
    private ListView listview ;
    private ListViewAdapter adapter;
    private ImageView home; //왼쪽 아래 홈버튼
    private TextView adress;// 왼쪽위 주소버튼
    private ImageView setting; //오른쪽 위 세팅버튼
    private ImageView bookmark; //중앙 아래 북마크버튼
    private ImageView search; //오른쪽 아래 검색버튼
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        list_fragment = new list_Fragment();

        //프래그먼트 매니저 획득
        FragmentManager fragmentManager = getSupportFragmentManager();

        //프래그먼트 Transaction 획득
        //프래그먼트를 올리거나 교체하는 작업을 Transaction이라고 합니다.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //프래그먼트를 FrameLayout의 자식으로 등록해줍니다.
        fragmentTransaction.add(R.id.fragmentFrame,list_fragment );

        //commit을 하면 자식으로 등록된 프래그먼트가 화면에 보여집니다.
        fragmentTransaction.commit();
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
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.detach(list_fragment).attach(list_fragment).commit();
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

