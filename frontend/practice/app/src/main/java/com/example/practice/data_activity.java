package com.example.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class data_activity extends AppCompatActivity {

    Call<List<data_model>> call;
    TextView textView;

    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);           // 출력
        textView =findViewById(R.id.text_view_result);

        // retrofit 호출
        //data_model dataModel = new data_model(name);
        //call = retrofit_client.getApiService().postOverlapCheck(dataModel); // interface post함수 가져오기
        call = retrofit_client.getApiService().test_api_get("서산"); // interface get함수 가져오기
        call.enqueue(new Callback<List<data_model>>(){
            //콜백 받는 부분
            @Override
            public void onResponse(Call<List<data_model>> call, Response<List<data_model>> response) {
                Log.e("error 3", "error 3");
                if (!response.isSuccessful()) {
                    Log.e("비정상적인 연결 : ", "Code : " + response.code());
                    return;
                }
                //Log.e("URL : ", call.toString());
                List<data_model> result = response.body() ;
                Log.e("정상적인 연결 : ", result.toString());
                String str;
                String content = "";
                for (data_model data_model : result) {
                    content += "NAME: " + data_model.getFaclNm() + "\n";
                    content += "TYPE CODE: " + data_model.getFaclTyCd() + "\n";
                    content += "ADDRESS: " + data_model.getLcMnad() + "\n\n";
                }
                textView.setText(content);
                //str = result.toString();
                //textView.setText(str);
            }

            @Override
            public void onFailure(Call<List<data_model>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}

