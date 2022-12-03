package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

public class bookmark_activity extends AppCompatActivity {

    private ListView listview;
    private com.example.practice.ListViewAdapter adapter;
    String[] bookmarkname = new String[1000];
    String[] bookmarkadress = new String[1000];
    String[] bookmarkLng = new String[1000];
    String[] bookmarkLat = new String[1000];
    String[] booknum=  new String[1000];
    String stri;

    int check = 0;
    String strcheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);


        adapter = new com.example.practice.ListViewAdapter();

        // 리스트뷰 참조 및 Adapter 달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);


        Intent intent = getIntent();
        bookmarkname = intent.getStringArrayExtra("str_name"); //스트링을 받음 시설명
        bookmarkadress = intent.getStringArrayExtra("str_address"); //스트링을 받음 시설명
        bookmarkLat = intent.getStringArrayExtra("lat"); //스트링을 받음 시설명
        bookmarkLng = intent.getStringArrayExtra("lng"); //스트링을 받음 시설명
        strcheck= intent.getStringExtra("check");
        check=Integer.parseInt(strcheck);


        for (int i=0; i<1000;i++){
            if(i %10==0){booknum[i]="043-230-6700";}
            else if(i %10==1) {booknum[i]="043-295-2505";}
            else if(i %10==2) {booknum[i]="043-264-1616";}
            else if(i %10==3) {booknum[i]="0507-1337-2701";}
            else if(i %10==4) {booknum[i]="043-267-5835";}
            else if(i %10==5) {booknum[i]="043-201-0001";}
            else if(i %10==6) {booknum[i]="043-236-0358";}
            else if(i %10==7) {booknum[i]="043-223-9428";}
            else if(i %10==8) {booknum[i]="043-716-2163";}
            else if(i %10==9) {booknum[i]="043-275-7411";}
        }

        for (int i=0; i<check;i++){
            if(i %10==0){
                adapter.addItem(bookmarkname[i], R.drawable.a2, bookmarkadress[i]);
            }else if(i %10==1) {
                adapter.addItem(bookmarkname[i], R.drawable.a3, bookmarkadress[i]);
            }
            else if(i %10==2) {
                adapter.addItem(bookmarkname[i], R.drawable.a4, bookmarkadress[i]);
            }
            else if(i %10==3) {
                adapter.addItem(bookmarkname[i], R.drawable.a1, bookmarkadress[i]);
            }
            else if(i %10==4) {
                adapter.addItem(bookmarkname[i], R.drawable.a5, bookmarkadress[i]);
            }
            else if(i %10==5) {
                adapter.addItem(bookmarkname[i], R.drawable.a6, bookmarkadress[i]);
            }
            else if(i %10==6) {
                adapter.addItem(bookmarkname[i], R.drawable.a7, bookmarkadress[i]);
            }
            else if(i %10==7) {
                adapter.addItem(bookmarkname[i], R.drawable.a8, bookmarkadress[i]);
            }
            else if(i %10==8) {
                adapter.addItem(bookmarkname[i], R.drawable.a9, bookmarkadress[i]);
            }
            else if(i %10==9) {
                adapter.addItem(bookmarkname[i], R.drawable.a10, bookmarkadress[i]);
            }

        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(bookmark_activity.this, info_activity.class); //위치지정
                intent.putExtra("str_name", bookmarkname[i]); //str1 쏴주기 반대편으로
                intent.putExtra("str_address", bookmarkadress[i]); //str2 쏴주기 반대편으로
                intent.putExtra("str_lng", bookmarkLng[i]);
                intent.putExtra("str_lat", bookmarkLat[i]);
                stri= Integer.toString(i);

                intent.putExtra("num",stri ); // 사진 순서
                intent.putExtra("aorp","2" );
                intent.putExtra("bk","1" );
                intent.putExtra("phone",booknum[i] );
                startActivity(intent); //액티비티 이동
            }
        });


    }
}