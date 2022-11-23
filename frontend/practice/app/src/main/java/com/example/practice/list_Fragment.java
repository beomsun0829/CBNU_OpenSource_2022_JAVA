package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


public class list_Fragment extends Fragment {


    private ListView listview ;
    private ListViewAdapter adapter;
    private TextView adress;// 왼쪽위 주소버튼
    private ImageView setting; //오른쪽 위 세팅버튼
    private ImageView home; //왼쪽 아래 홈버튼
    private ImageView bookmark; //중앙 아래 북마크버튼
    private ImageView search; //오른쪽 아래 검색버튼

    private SwipeRefreshLayout swipeRefreshLayout;




    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_list_, container, false);
        // Adapter 생성
        adapter = new ListViewAdapter();

        // 리스트뷰 참조 및 Adapter 달기
        listview = (ListView) view.findViewById(R.id.listview);
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
                Intent intent =  new Intent(getActivity(), info_activity.class); //위치지정
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent); //액티비티 이동
            }
        });

        return view;
    }
}
