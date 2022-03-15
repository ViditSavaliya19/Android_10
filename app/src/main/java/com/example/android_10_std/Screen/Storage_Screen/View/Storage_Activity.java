package com.example.android_10_std.Screen.Storage_Screen.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android_10_std.R;
import com.example.android_10_std.Screen.Storage_Screen.controller.strorage_controller;

public class Storage_Activity extends AppCompatActivity {

    private Button gallery_btn;
    private ImageView img;
    private Button upload_btn,read_btn;
    private Button permission_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        binding();
        strorage_controller sc= new strorage_controller(Storage_Activity.this);

        gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,100);
            }
        });

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
                sc.UploadData(bitmap);
            }
        });


        read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sc.ReadImages();
            }
        });

        permission_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,100);
            }
        });

    }

    private void binding() {
        gallery_btn=findViewById(R.id.gallery_btn);
        img=findViewById(R.id.img);
        upload_btn=findViewById(R.id.upload_btn);
        read_btn=findViewById(R.id.read_btn);
        permission_btn=findViewById(R.id.permission_btn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(100==requestCode)
        {
            Uri uri = data.getData();
            img.setImageURI(uri);
        }
    }



    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(Storage_Activity.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(Storage_Activity.this, new String[] { permission }, requestCode);
        }
        else {
            Toast.makeText(Storage_Activity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Storage_Activity.this, "Camera Permission Granted", Toast.LENGTH_SHORT) .show();
            }
            else {
                Toast.makeText(Storage_Activity.this, "Camera Permission Denied", Toast.LENGTH_SHORT) .show();
            }
        }
        else if (requestCode == 101) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Storage_Activity.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Storage_Activity.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}