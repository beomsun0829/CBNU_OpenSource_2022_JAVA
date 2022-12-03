package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class search_result extends AppCompatActivity {

    private ListView listview;
    private com.example.practice.ListViewAdapter adapter;
    Call<List<data_model>> call;


    String[] namelist=new String[1000];
    String[] fadresslist=new String[1000];
    String[] Lnglist = new String[1000]; //경도
    String[] Latlist = new String[1000]; //위도
    String[] wfcltId = new String[1000]; // 시설코드
    String stri;
    String[] searchnum=  new String[1000];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);



        Intent intent = getIntent();
        String search_text = intent.getStringExtra("text"); //스트링을 받음 시설명
        String cur_lng = intent.getStringExtra("str_lat"); //스트링을 받음 경도
        String cur_lat = intent.getStringExtra("str_lng"); //스트링을 받음 위도

        // Adapter 생성
        adapter = new ListViewAdapter();
        // 리스트뷰 참조 및 Adapter 달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        call = retrofit_client.getApiService().get_Nm_Lat_Lng(search_text,cur_lat,cur_lng); // interface get함수 가져오기
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


                for (int i=0; i<1000;i++){
                    if(i %10==0){searchnum[i]="0507-1337-2701";}
                    else if(i %10==1) {searchnum[i]="043-230-6700";}
                    else if(i %10==2) {searchnum[i]="043-264-1616";}
                    else if(i %10==3) {searchnum[i]="043-267-5835";}
                    else if(i %10==4) {searchnum[i]="043-223-9428";}
                    else if(i %10==5) {searchnum[i]="043-716-2163";}
                    else if(i %10==6) {searchnum[i]="043-275-8025";}
                    else if(i %10==7) {searchnum[i]="043-216-1313";}
                    else if(i %10==8) {searchnum[i]="043-716-2163";}
                    else if(i %10==9) {searchnum[i]="043-236-8302";}
                }

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
                    wfcltId[i]= data_model.getWfcltId();
//                    Log.e("정상적인 연결 : ", namelist[i]);
//                    Log.e("정상적인 연결 : ", fadresslist[i]);
                    if(i %10==0){
                        adapter.addItem(namelist[i], R.drawable.p1, fadresslist[i]);
                    }else if(i %10==1) {
                        adapter.addItem(namelist[i], R.drawable.p2, fadresslist[i]);
                    }
                    else if(i %10==2) {
                        adapter.addItem(namelist[i], R.drawable.p3, fadresslist[i]);
                    }
                    else if(i %10==3) {
                        adapter.addItem(namelist[i], R.drawable.p4, fadresslist[i]);
                    }
                    else if(i %10==4) {
                        adapter.addItem(namelist[i], R.drawable.p5, fadresslist[i]);
                    }
                    else if(i %10==5) {
                        adapter.addItem(namelist[i], R.drawable.p6, fadresslist[i]);
                    }
                    else if(i %10==6) {
                        adapter.addItem(namelist[i], R.drawable.p7, fadresslist[i]);
                    }
                    else if(i %10==7) {
                        adapter.addItem(namelist[i], R.drawable.p8, fadresslist[i]);
                    }
                    else if(i %10==8) {
                        adapter.addItem(namelist[i], R.drawable.p9, fadresslist[i]);
                    }
                    else if(i %10==9) {
                        adapter.addItem(namelist[i], R.drawable.p10, fadresslist[i]);
                    }
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


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(search_result.this, info_activity.class); //위치지정
                intent.putExtra("str_name",namelist[i]); //str1 쏴주기 반대편으로
                intent.putExtra("str_address",fadresslist[i]); //str2 쏴주기 반대편으로
                intent.putExtra("str_lng",Lnglist[i]); //str3 쏴주기 반대편으로
                intent.putExtra("str_lat",Latlist[i]); //str4 쏴주기 반대편으로
                intent.putExtra("str_WfcltId",wfcltId[i]); //시설 코드 쏴주기 반대편으로
                stri= Integer.toString(i);
                intent.putExtra("num",stri ); // 사진 순서
                intent.putExtra("aorp","1"); //aorp
                intent.putExtra("bk","0" );
                intent.putExtra("phone",searchnum[i] );
                startActivity(intent); //액티비티 이동
            }
        });
    }
}