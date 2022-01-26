package com.example.handaroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SufShowitemActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    ImageView Img_Pillinfo;
    TextView volumn;
    TextView ingredient;
    TextView company;
    TextView deatil_pill_name;
    ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_detail_item);
        Img_Pillinfo = findViewById(R.id.Img_Pillinfo);
        volumn = findViewById(R.id.volumn);
        volumn.setMovementMethod(new ScrollingMovementMethod());
        ingredient = findViewById(R.id.ingredient);
        company = findViewById(R.id.detail_make_pill);
        deatil_pill_name = findViewById(R.id.detail_pill_name);
        requestQueue= Volley.newRequestQueue(getApplicationContext());

        btn_back = findViewById(R.id.btn_back);




        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SufLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });




        String get_url="http://192.168.0.103:8081/handaroid/PillSearchController";
        String url ="http://192.168.0.103:8081/handaroid/PillSearchController";
        String pill = getIntent().getStringExtra("pill");
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


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

                params.put("pill", pill);


                return params;
            }
        };
        requestQueue.add(request);


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
                            for(int i = 0; i < json_array.length(); i++){
                                JSONObject Pill = (JSONObject) json_array.get(i);
                                String name = Pill.getString("pills_name");
                                String pill_ingredient = Pill.getString("pills_ingredient");
                                String effic = Pill.getString("pills_efficacy");
                                String pill_company = Pill.getString("pills_company");
//                                byte[] arr = getIntent().getByteArrayExtra("image");

                                Bundle extras = getIntent().getExtras();
                                String s = extras.getString("string");
                                int ib = extras.getInt("integer");
                                double d = extras.getDouble("double");
                                byte[] byteArray = getIntent().getByteArrayExtra("image");
                                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                                Img_Pillinfo.setImageBitmap(bitmap);
                                deatil_pill_name.setText(name);
                                ingredient.setText(pill_ingredient);
                                volumn.setText(effic);
                                company.setText(pill_company);


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

    }
}