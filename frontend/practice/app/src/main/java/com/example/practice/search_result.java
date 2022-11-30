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

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(search_result.this, info_activity.class); //위치지정
                intent.putExtra("str_name",namelist[i]); //str1 쏴주기 반대편으로
                intent.putExtra("str_address",fadresslist[i]); //str2 쏴주기 반대편으로
                intent.putExtra("str_lng",Lnglist[i]); //str3 쏴주기 반대편으로
                intent.putExtra("str_lat",Latlist[i]); //str4 쏴주기 반대편으로
                startActivity(intent); //액티비티 이동
            }
        });




    }
}