package com.example.handaroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class alarm_list extends AppCompatActivity {
    ListView lv;
    ArrayList<AlarmVO> data;
    RequestQueue requestQueue;
    AlarmAdapter adapter;
    Button btnadd;
    ImageButton btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);
        String id = getIntent().getStringExtra("id");

        lv=(ListView)findViewById(R.id.alarmlist);
        data=new ArrayList<>();

        btnadd = findViewById(R.id.btn_add);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alarm = new Intent(getApplicationContext(),SetAlarm.class);
                startActivity(alarm);

            }
        });


        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }





        //JSP연동.json파싱해서 ArrayList에 추가
        String url="http://192.168.0.103:8081/handaroid/AlarmViewController";



                StringRequest stringRequest = new StringRequest(
                        Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // 응답 성공시 -> response -> JSON타입의 문자열로 전송
                                JSONArray json_array = null;
                                try {
                                    json_array = new JSONArray(response);

                                    // movies 배열에 차례대로 접근할 수 있는 for문 생성하기
                                    for(int i = 0; i < json_array.length(); i++){
                                        JSONObject alarm = (JSONObject) json_array.get(i);
                                        System.out.println(alarm);

//                                        Log.d("결과", movie.getString("movieNm"));

                                        AlarmVO vo = new AlarmVO(Integer.parseInt(alarm.getString("alarm_seq")),
                                                alarm.getString("text"),
                                                alarm.getString("hours")
                                        ,alarm.getString("minutes"));

                                        data.add(vo);

                                    }

                                    adapter.notifyDataSetChanged();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // 응답 실패시

                            }
                        }

                );

                requestQueue.add(stringRequest);

        adapter = new AlarmAdapter(getApplicationContext(),R.layout.alarmlist1, data);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btnback = findViewById(R.id.btn_back3);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MypageActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });


    }

}

