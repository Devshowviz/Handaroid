package com.example.handaroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class ModifyAlarm extends AppCompatActivity {

    private TimePicker timePicker;
    private EditText editText;
    private AlarmManager alarmManager;
    SQLiteDatabase mDb;
    Button regist;
    private int hour, minute;
    private String hours;
    private String minutes;
    public String alarmtime;
    private String text, ampm;
    String inAmpm;
    String inHour;
    String inMinute;
    String inDrug;
    public AlarmDbHelper alarmDbHelper;
    public String cancelId;
    public PendingIntent pIntent;
    public Intent intent;
    public Databases databases;
    RequestQueue requestQueue;

    @Override
    public void onBackPressed() {
        // 버튼을 누르면 메인화면으로 이동
        Intent intent = new Intent(getApplicationContext(), SufLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_alarm);
        requestQueue = Volley.newRequestQueue(getApplicationContext());


        timePicker = (TimePicker) findViewById(R.id.timepicker);
        editText = (EditText) findViewById(R.id.editText);
        timePicker.setIs24HourView(false);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);


        regist = (Button)findViewById(R.id.btnset);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();
                text = editText.getText().toString();
                hours = String.valueOf(hour);
                minutes = String.valueOf(minute);
                alarmtime = String.valueOf(hour)+minute;

                if(Build.VERSION.SDK_INT <23){

                    if(hour>=12 && hour<24){
                        ampm = "오후";
                    }
                    else{
                        ampm = "오전";
                    }

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(Databases.CreateDB.AMPM, ampm);
                    contentValues.put(Databases.CreateDB.HOUR, hour);
                    contentValues.put(Databases.CreateDB.MINUTE, minute);
                    contentValues.put(Databases.CreateDB.DRUGTEXT, text);
                    contentValues.put(Databases.CreateDB.ALARMTIME, alarmtime);

                    mDb = AlarmDbHelper.getInstance(getApplicationContext()).getWritableDatabase();
                    mDb.insert(Databases.CreateDB.TABLE_NAME,null,contentValues);

                    Calendar calendar = Calendar.getInstance();

                    calendar.set(Calendar.HOUR_OF_DAY,hour);
                    calendar.set(Calendar.MINUTE,minute);
                    calendar.set(Calendar.SECOND,0);


                    long intervalTime = 1000*60*60*24; //24시간
                    long currentTime = System.currentTimeMillis();

                    if(currentTime>calendar.getTimeInMillis()){
                        calendar.setTimeInMillis(calendar.getTimeInMillis()+intervalTime);
                    }
                    Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);

                    intent.putExtra("id" ,alarmtime);
                    intent.putExtra("drug", text);

                    pIntent = PendingIntent.getBroadcast(getApplicationContext(), Integer.parseInt(alarmtime), intent,0);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),intervalTime,pIntent);
                    Toast.makeText(getApplicationContext(),"알림이 설정되었습니다.", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);



                }
                else{

                    if(hour>=12 && hour<24){
                        ampm = "오후";
                    }
                    else{
                        ampm = "오전";
                    }

                    Calendar calendar = Calendar.getInstance();

                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE,minute);
                    calendar.set(Calendar.SECOND,0);

                    long intervalTime = 1000*60*60*24; //24시간
                    long currentTime = System.currentTimeMillis();

                    if(currentTime>calendar.getTimeInMillis()){
                        calendar.setTimeInMillis(calendar.getTimeInMillis()+intervalTime);
                    }
                    intent = new Intent(getApplicationContext(), AlarmReceiver.class);

                    //alarmDbHelper.onCreate(mDb);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(Databases.CreateDB.AMPM, ampm);
                    contentValues.put(Databases.CreateDB.HOUR, hour);
                    contentValues.put(Databases.CreateDB.MINUTE, minute);
                    contentValues.put(Databases.CreateDB.DRUGTEXT, text);
                    contentValues.put(Databases.CreateDB.ALARMTIME, alarmtime);

                    intent.putExtra("id" , alarmtime);
                    intent.putExtra("drug", text);

                    Log.e("intent확인 id: ", alarmtime);
                    Log.e("intent확인 drug: ", text);

                    mDb = AlarmDbHelper.getInstance(getApplicationContext()).getWritableDatabase();
                    mDb.insert(Databases.CreateDB.TABLE_NAME,null,contentValues);


                    PendingIntent pIntent = PendingIntent.getBroadcast(getApplicationContext(), Integer.parseInt(alarmtime), intent,0);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),intervalTime,pIntent);
                    Toast.makeText(getApplicationContext(),"알림이 설정되었습니다.", Toast.LENGTH_SHORT).show();

                    setResult(RESULT_OK);

                    //Intent intent = new Intent(SetAlarm.this, alarmlist.class);
                    //startActivity(intent);
                }

                String url="http://192.168.0.103:8081/handaroid/AlarmUpdateController";

                //StringResust 활용하여 기본 틀 제작하기!
                // 전송방식-> POST-> 기본 틀 + getParams()
                StringRequest request= new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //응답성공시
                                    Intent alarm=new Intent(getApplicationContext(), alarm_list.class);
                                    startActivity(alarm);
                                    finish();

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
                        params.put("alarm_seq",getIntent().getStringExtra("alarm_seq"));
                        params.put("text", text);
                        params.put("hours", hours);
                        params.put("minutes",minutes);


                        return params;
                    }
                };
                requestQueue.add(request);
            }
        });

    }

}