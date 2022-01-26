package com.example.handaroid;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowMapActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    TextView detail_pill_name;
    TextView detail_make_pill;
    ListView lv;
    ArrayList<MapVO> data;
    MapAdapter adapter;
    ImageView btnBack;
    TextView context1,context2,context3,context4,context5,context6,context7;
    private byte[] filebyte;
    private File file;
    private File dir;
    Button btn_Voice;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_detail_map);
        detail_pill_name = findViewById(R.id.detail_pill_name);
        detail_make_pill = findViewById(R.id.detail_make_pill);
        context1 = findViewById(R.id.context1);
        context2 = findViewById(R.id.context2);
        context3 = findViewById(R.id.context3);
        context4 = findViewById(R.id.context4);
        context5 = findViewById(R.id.context5);
        context6 = findViewById(R.id.context6);
        context7 = findViewById(R.id.context7);
        btn_Voice = findViewById(R.id.btn_Voice);
        btn_Voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer = MediaPlayer.create(ShowMapActivity.this, R.raw.boong3);
                mediaPlayer.start();

            }
        });

        data = new ArrayList<>();

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }


        String get_url = "http://192.168.0.103:8081/handaroid/board/MypageId.jsp";
        //JSP연동.json파싱해서 ArrayList에 추가
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                get_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 응답 성공시 -> response -> JSON타입의 문자열로 전송
                        JSONArray json_array = null;
                        try {
                            json_array = new JSONArray(response);

                            // movies 배열에 차례대로 접근할 수 있는 for문 생성하기
                            for (int i = 0; i < json_array.length(); i++) {
                                JSONObject Map = (JSONObject) json_array.get(i);
                                String name = Map.getString("name");
                                String date = Map.getString("date");
                                String context11 = Map.getString("context1");
                                String context22 = Map.getString("context2");
                                String context33 = Map.getString("context3");
                                String context44 = Map.getString("context4");
                                String context55 = Map.getString("context5");
                                String context66 = Map.getString("context6");
                                String context77 = Map.getString("context7");


                                detail_pill_name.setText(name);
                                detail_make_pill.setText(date);
                                context1.setText(context11);
                                context2.setText(context22);
                                context3.setText(context33);
                                context4.setText(context44);
                                context5.setText(context55);
                                context6.setText(context66);
                                context7.setText(context77);


                            }


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



        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                mediaPlayer.stop();
                startActivity(intent);
                finish();
            }
        });



    }

}


