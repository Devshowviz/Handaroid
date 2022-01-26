package com.example.handaroid;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.zip.Inflater;

public class SufLoginActivity extends AppCompatActivity {

    ImageButton btn_medimap;
    ImageButton btn_medi;
    ImageButton btn_alarm2;
    ImageButton btn_mypage;
    String id;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suf_login);

        btn_medimap = findViewById(R.id.btn_medimap);
        btn_medi = findViewById(R.id.btn_medi);
        btn_alarm2 = findViewById(R.id.btn_alarm2);
        btn_mypage = findViewById(R.id.btn_mypage);
        id = getIntent().getStringExtra("id");


        btn_alarm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SufLoginActivity.this, SetAlarm.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }


        });
        btn_medimap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SufLoginActivity.this,AfterCameraMap.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });
        btn_medi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SufLoginActivity.this,AfterPillSearchActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MypageActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
}}




