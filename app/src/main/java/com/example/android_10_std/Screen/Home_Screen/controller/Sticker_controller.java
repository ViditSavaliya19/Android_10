package com.example.android_10_std.Screen.Home_Screen.controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.android_10_std.Screen.Home_Screen.View.Home_Activity;
import com.example.android_10_std.Utils.StickerView;

public class Sticker_controller {
    Activity activity;
    public static StickerView mCurrentView;

    public Sticker_controller(Activity activity) {
        this.activity=activity;
    }

    public void sticker_Create(Bitmap bt, FrameLayout frame) {
        StickerView stickerView = new StickerView(activity);
//        stickerView.setImageResource(R.drawable.dice);
        stickerView.setBitmap(bt);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        frame.addView(stickerView, layoutParams);

        setCurrentEdit(stickerView);


        stickerView.setOperationListener(new StickerView.OperationListener() {
            @Override
            public void onDeleteClick() {
                frame.removeView(stickerView);
            }

            @Override
            public void onEdit(StickerView stickerView) {
                mCurrentView.setInEdit(false);
                mCurrentView = stickerView;
                mCurrentView.setInEdit(true);
            }

            @Override
            public void onTop(StickerView stickerView) {

            }
        });
    }

    private void setCurrentEdit(StickerView stickerView) {
        if (mCurrentView != null) {
            mCurrentView.setInEdit(false);
        }
        mCurrentView = stickerView;
        stickerView.setInEdit(true);
    }


}
