package com.example.handaroid;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class
MapAdapter extends BaseAdapter {
    //일반 class로 만들어진 파일을 Adapter의 형태로 변환시켜야한다.
    //->기본 Adapter인  BaseAdapter 상속받기


    //Adapter연결시 필요한 정보들을 지정할 수 있는 변수 생성하기
    //context,laout,실제 데이터(ArrayList - kakao)
    Context context;
    int layout;
    RequestQueue requestQueue;
    //레이아웃에 띄워질 ArrayList 생성하기
    ArrayList<MapVO>map = new ArrayList<>();






    //메인으로부터 전달받아 adapter을 생성해 줄 수 있는 생성자 메소드 만들기
    //adapter 용 생성자 메소드 ->현재 어플의 정보,layout,data
    public MapAdapter(Context context, int layout, ArrayList<MapVO> data){
        this.context = context;
        this.layout = layout;
        this.map=data;
    }

    @Override
    public int getCount() {
        //만들어진 ArrayList의 데이터 갯수를 반환해주는메소드
        return map.size();
    }

    @Override
    public Object getItem(int position) {
        //position번째 항목을 리턴해주는 메소드
        return map.get(position);
    }

    @Override
    public long getItemId(int position) {
        //position 번째 항목의 아이디를 반환해주는메소드
        //ID는 position과 같은 의미로 쓰인다.
        return position;
    }

    //가장가장가장중요한메소드!!!!!!!!!!!!!!!!  필수구현!!!!
    //listview에 들어갈 item 내용을 초기화하고
    //화면에 보일수 있도록 값을 돌려주는 메소드!!!
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView : 각각의 View를 불러올수 있는 변수!
        //parent : listview를 의미하는 변수 ->사용 X


        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mapllist1, null);
        }
        String get_url = "http://192.168.0.103:8081/handaroid/board/MypageId.jsp";
        //JSP연동.json파싱해서 ArrayList에 추가
        View finalConvertView = convertView;
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
                                JSONObject Map = (JSONObject) json_array.get(i);
                                String context1 = Map.getString("context1");
                                String context2 = Map.getString("context2");
                                String context3 = Map.getString("context3");
                                String context4 = Map.getString("context4");
                                String context5 = Map.getString("context5");
                                String context6 = Map.getString("context6");
                                String context7 = Map.getString("context7");
                                TextView tvName = finalConvertView.findViewById(R.id.tvName);
                                tvName.setText(map.get(position).getMap_context1());




//                                Bitmap bm = BitmapFactory.decodeByteArray(arr, 0, arr.length);
//                                Img_Pillinfo.setImageBitmap(bm);



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



        return convertView;


    }


}
