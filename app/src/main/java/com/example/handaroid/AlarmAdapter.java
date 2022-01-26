package com.example.handaroid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class
AlarmAdapter extends BaseAdapter {
    //일반 class로 만들어진 파일을 Adapter의 형태로 변환시켜야한다.
    //->기본 Adapter인  BaseAdapter 상속받기


    //Adapter연결시 필요한 정보들을 지정할 수 있는 변수 생성하기
    //context,laout,실제 데이터(ArrayList - kakao)
    Context context;
    int layout;
    RequestQueue requestQueue;
    //레이아웃에 띄워질 ArrayList 생성하기
    ArrayList<AlarmVO>alarm = new ArrayList<>();






    //메인으로부터 전달받아 adapter을 생성해 줄 수 있는 생성자 메소드 만들기
    //adapter 용 생성자 메소드 ->현재 어플의 정보,layout,data
    public AlarmAdapter(Context context, int layout, ArrayList<AlarmVO> data){
        this.context = context;
        this.layout = layout;
        this.alarm=data;
    }

    @Override
    public int getCount() {
        //만들어진 ArrayList의 데이터 갯수를 반환해주는메소드
        return alarm.size();
    }

    @Override
    public Object getItem(int position) {
        //position번째 항목을 리턴해주는 메소드
        return alarm.get(position);
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
            convertView = inflater.inflate(R.layout.alarmlist1, null);
        }

         //kakaolist 에 있는 각각의 view를 찾아와 convertview로 내용 연결하기
        //Imageview,TextView,TextView
        //convertview ->setContentView(R.layout.activity_main) q비슷;
//        ImageView img = convertView.findViewById(R.id.);
        //실제 데이터를 find해온 view에 연결하는 작업
//        img.setImageResource(kakao.get(position).getImg());


        TextView tvName = convertView.findViewById(R.id.tvName);
        tvName.setText(alarm.get(position).getText());
        TextView tvTime = convertView.findViewById(R.id.tvTime);
        tvTime.setText(alarm.get(position).getHours());
        TextView tvMinutes = convertView.findViewById(R.id.tvMinutes);
        tvMinutes.setText(alarm.get(position).getMinutes());
        Button btnTime = convertView.findViewById(R.id.btnup);
        Button btnDel = convertView.findViewById(R.id.btnDel);
        Button btnup = convertView.findViewById(R.id.btnup);
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent time =new Intent(context.getApplicationContext(), SetAlarm.class);
                time.putExtra("alarmup",1);
                time.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(time);
            }
        });

        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update =new Intent(context.getApplicationContext(), ModifyAlarm.class);
                update.putExtra("alarm_seq",String.valueOf(alarm.get(position).getAlarm_seq()));
                update.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(update);
            }
        });



        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestQueue = Volley.newRequestQueue(context.getApplicationContext());


                String url ="http://192.168.0.103:8081/handaroid/AlarmDeleteController";
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                alarm.remove(alarm.get(position));
                                notifyDataSetChanged();
                                Intent alarm2 = new Intent(context.getApplicationContext(),alarm_list.class);
                                alarm2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(alarm2);
                                Toast.makeText(context.getApplicationContext(), "삭제 성공", Toast.LENGTH_SHORT).show();

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
                        params.put("alarm_seq", String.valueOf(alarm.get(position).getAlarm_seq()));
                        notifyDataSetChanged();


                        return params;
                    }
                };

                requestQueue.add(request);
                notifyDataSetChanged();


            }


        });
       //return을 수정하지 않으면 null값으로 화면에 아무것도 보이지 않는다.

        return convertView;


    }


}
