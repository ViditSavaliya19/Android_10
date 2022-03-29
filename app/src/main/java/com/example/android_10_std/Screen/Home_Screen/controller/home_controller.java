package com.example.android_10_std.Screen.Home_Screen.controller;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;

public class home_controller {
    Activity activity;
    public home_controller(Activity activity) {
        this.activity=activity;
    }

    public void getAllImages()
    {
        File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Canva/");
        if(folder.exists()) {
            File[] allFiles = folder.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return (name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png"));
                }
            });


            for (int i=0;i<allFiles.length;i++)
            {
                Log.e("TAG", "getAllImages: "+allFiles[i].getPath() );
            }
        }
        else
        {
            Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
        }

    }
}
