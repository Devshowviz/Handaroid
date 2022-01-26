package com.example.handaroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ImageButton btn_medimap,btn_medi;
    Button btn_login2,btn_Sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_medimap = findViewById(R.id.btn_medimap);
        btn_medi = findViewById(R.id.btn_medi);
        btn_login2 = findViewById(R.id.btn_login2);
        btn_Sign = findViewById(R.id.btn_Sign);

        btn_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
                startActivityForResult(intent,100);

            }
        });

        btn_Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),signup.class);
                startActivityForResult(intent,100);

            }
        });

        btn_medimap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),CameraMap.class);
//                startActivityForResult(intent,100);
                startActivity(intent);



            }
        });

        btn_medi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),PillSearchActivity.class);
//                startActivityForResult(intent,100);
                startActivity(intent);
            }
        });




    }
}