package com.example.handaroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    final Context context = this;

    EditText signup_PW, signup_ID, signup_PW_check;
    Button btn_Sign;

    String sId, sPw, sPw_chk;
    RequestQueue requestQueue;
    ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        signup_ID = findViewById(R.id.signup_ID);
        signup_PW = findViewById(R.id.signup_PW);
        signup_PW_check = findViewById(R.id.signup_PW_check);
        btn_Sign = findViewById(R.id.btn_Sign);
        btn_back=findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


        requestQueue = Volley.newRequestQueue(getApplicationContext());
        btn_Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id =signup_ID.getText().toString();
                String pw =signup_PW.getText().toString();

                String url="http://192.168.0.103:8081/handaroid/JoinController";

                //StringResust 활용하여 기본 틀 제작하기!
                // 전송방식-> POST-> 기본 틀 + getParams()
                StringRequest request= new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //응답성공시
                                if(response.equals("1")){
                                    Intent Login_intent=new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(Login_intent);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(),"가입실패",Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("error", error.toString());
                            }
                        }
                ){

                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        params.put("join_id", id);
                        params.put("join_pw", pw);


                        return params;
                    }
                };
                requestQueue.add(request);
            }
        });
    }





    public void after_back(View v) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.transition.anim_slide_a, R.transition.anim_slide_b);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Toast.makeText(getApplicationContext()," 뒤로가기가 눌렸습니다.", Toast.LENGTH_SHORT).show();
    }
}

