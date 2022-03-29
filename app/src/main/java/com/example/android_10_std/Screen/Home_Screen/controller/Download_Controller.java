package com.example.android_10_std.Screen.Home_Screen.controller;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Activity;
import android.app.DownloadManager;
import android.graphics.BlurMaskFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Download_Controller {

    Activity activity;
    public Download_Controller(Activity activity) {
        this.activity=activity;
    }

    protected void applyBlurMaskFilter(TextView tv, BlurMaskFilter.Blur style) {
        float radius = tv.getTextSize() / 10;
        BlurMaskFilter filter = new BlurMaskFilter(radius, style);
        tv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        tv.getPaint().setMaskFilter(filter);
    }


    private void download() {
        Downback DB = new Downback();
        DB.execute("");

    }


    private class Downback extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            final String vidurl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";
            downloadfile(vidurl);
            return null;

        }


    }

    private void downloadfile(String vidurl) {

        SimpleDateFormat sd = new SimpleDateFormat("yymmhh");
        String date = sd.format(new Date());
        String name = "video" + date + ".mp4";

        try {
            String rootDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    + File.separator + "My_Video";
            File rootFile = new File(rootDir);
            rootFile.mkdir();
            URL url = new URL(vidurl);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();
            FileOutputStream f = new FileOutputStream(new File(rootFile,
                    name));
            InputStream in = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while (in.available() > 0) {
                len1 = in.read(buffer);
                f.write(buffer, 0, len1);
            }

            Log.d("Success....", "" + len1);
            Log.d("Success....", "" + rootFile);

            f.close();
        } catch (IOException e) {
            Log.d("Error....", e.toString());
        }


    }

    @JavascriptInterface
    public void processVideo(final String vidData, final String vidID) {
        try {
            String mBaseFolderPath = android.os.Environment
                    .getExternalStorageDirectory()
                    + File.separator
                    + "FacebookVideos" + File.separator;
            if (!new File(mBaseFolderPath).exists()) {
                new File(mBaseFolderPath).mkdir();
            }
            String mFilePath = "file://" + mBaseFolderPath + "/" + vidID + ".mp4";

            Uri downloadUri = Uri.parse(vidData);
            DownloadManager.Request req = new DownloadManager.Request(downloadUri);
            req.setDestinationUri(Uri.parse(mFilePath));
            req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            DownloadManager dm = (DownloadManager) activity.getSystemService(activity.DOWNLOAD_SERVICE);
            dm.enqueue(req);
            Toast.makeText(activity, "Download Started", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(activity, "Download Failed: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
