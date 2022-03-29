package com.example.android_10_std.Screen.Home_Screen.View;

import static com.example.android_10_std.Screen.Home_Screen.controller.Sticker_controller.mCurrentView;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Shader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_10_std.R;
import com.example.android_10_std.Screen.Home_Screen.controller.Sticker_controller;
import com.example.android_10_std.Screen.Home_Screen.controller.home_controller;
import com.example.android_10_std.Utils.CustomTextView;
import com.example.android_10_std.Utils.Effects;
import com.example.android_10_std.Utils.StickerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Home_Activity extends AppCompatActivity {

    private TextView hello;
    private Button one, two, three, four;
    private static FrameLayout id_imageFrame;
    ImageView img;
    private home_controller home_controller1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        hello = findViewById(R.id.hello);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        img = findViewById(R.id.img);
        id_imageFrame = findViewById(R.id.id_imageFrame);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);


        home_controller1=new home_controller(this);
        Sticker_controller sticker_controller=new Sticker_controller(this);

        id_imageFrame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mCurrentView != null) {
                    mCurrentView.setInEdit(false);
                }
                return false;
            }
        });


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_controller1.getAllImages();
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Effects.applyEffect22(img);


            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hello.buildDrawingCache();
                Bitmap bitmap = hello.getDrawingCache();
                sticker_controller.sticker_Create(bitmap,id_imageFrame);


            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_controller1.getAllImages();
            }
        });


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        hello.getPaint().setShader(shader);
    }




}