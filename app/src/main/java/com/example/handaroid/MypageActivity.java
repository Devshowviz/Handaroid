package com.example.handaroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class MypageActivity extends AppCompatActivity {
    ImageButton btn_alarm;
    ImageButton btn_back2;
    ImageButton btn_medimap2;
    Button lobut;
    TextView id;
    String id1;
    private ImageView img;
    private TextView tvPillname;
    private Button btn_capture, btn_gallery, btn_send;
    private ProgressDialog progress;

    private RequestQueue queue;
    private String currentPhotoPath;
    private Bitmap bitmap;
    private String pill_name;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int GET_GALLERY_IMAGE = 2;

    static final String TAG = "카메라";
    private String imageString;
    private Uri uri;
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private Uri mImageCaptureUri;
    private ImageView mPhotoImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        btn_alarm=findViewById(R.id.btn_alarm);
        btn_back2=findViewById(R.id.btn_back2);
        btn_medimap2 = findViewById(R.id.btn_medimap2);
        lobut=findViewById(R.id.lobut);
        id = findViewById(R.id.tvid);
        id1 = getIntent().getStringExtra("id");
        id.setText(id1);




        btn_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SufLoginActivity.class);
                intent.putExtra("id",id1);
                startActivity(intent);
            }

        });



                btn_alarm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), alarm_list.class);
                        intent.putExtra("id",id1);
                        startActivity(intent);
                    }
                });

                lobut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }
                });

                btn_medimap2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),MyPageCameraMap.class);
                        intent.putExtra("id",id1);
                        startActivity(intent);

                        }








                });

            }

    }



