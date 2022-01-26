package com.example.handaroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText edtId, edtpw;
    Button btnLogin, btnJoin;
    RequestQueue requestQueue;
    ImageButton btn_back;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // 1. view id 찾아오기

        edtId= findViewById(R.id.et_id);
        edtpw=findViewById(R.id.et_pw);

        btnLogin=findViewById(R.id.btn_login2);
        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });



        //3. 로그인 버튼 클릭시 서버에 요청 넣어서 결과값 받아오기
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestQueue= Volley.newRequestQueue(getApplicationContext());
                String id=edtId.getText().toString();
                String pw=edtpw.getText().toString();


                String url ="http://192.168.0.103:8081/handaroid/LoginController";
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("1")){
                                    Intent Main_intent=new Intent(getApplicationContext(), SufLoginActivity.class);
                                    Main_intent.putExtra("id",id);
                                    startActivity(Main_intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                                }else{
                                    Intent not_login = new Intent(getApplicationContext(),LoginActivity.class);
                                    startActivity(not_login);
                                    Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                                }
                            }


                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                ){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();

                        params.put("login_id", id);
                        params.put("login_pw", pw);


                        return params;
                    }
                };
                requestQueue.add(request);
            }
        });




    }
    public void after_back(View v) {
        Intent intent = new Intent(getApplicationContext(), SufLoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.transition.anim_slide_a, R.transition.anim_slide_b);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Toast.makeText(getApplicationContext()," 뒤로가기가 눌렸습니다.", Toast.LENGTH_SHORT).show();
    }
}