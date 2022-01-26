package com.example.handaroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class f2 extends AppCompatActivity {
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f1);


        Handler hand = new Handler();

        hand.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                id = getIntent().getStringExtra("id");
                Intent i = new Intent(f2.this, SufShowMapActivity.class);
                i.putExtra("id",id);
                startActivity(i);
                finish();


            }
        }, 2000);


    }
}






