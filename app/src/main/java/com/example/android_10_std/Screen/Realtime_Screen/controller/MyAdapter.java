package com.example.android_10_std.Screen.Realtime_Screen.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_10_std.R;
import com.example.android_10_std.Screen.Realtime_Screen.Model.ModelData;
import com.example.android_10_std.Screen.Realtime_Screen.View.realtime_Activity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewData> {
    Activity activity;
    List<ModelData> l1;

    public MyAdapter(realtime_Activity realtime_activity, List<ModelData> list) {
        activity = realtime_activity;
        l1 = list;
    }

    @NonNull
    @Override
    public ViewData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item, parent, false);
        return new ViewData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewData holder, @SuppressLint("RecyclerView") int position) {
        holder.name_txt.setText(l1.get(position).getName());
        holder.grid_txt.setText(l1.get(position).getGrid());
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference db = firebaseDatabase.getReference();

        holder.edit_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ModelData modelData=new ModelData("Sahil","4918","12121212");
                db.child("Student").child(l1.get(position).getKey()).setValue(modelData);


                

            }
        });


    }

    @Override
    public int getItemCount() {
        return l1.size();
    }

    class ViewData extends RecyclerView.ViewHolder {
        TextView grid_txt, name_txt;
        ImageView edit_img;

        public ViewData(@NonNull View itemView) {
            super(itemView);

            grid_txt = itemView.findViewById(R.id.grid_txt);
            name_txt = itemView.findViewById(R.id.name_txt);
            edit_img = itemView.findViewById(R.id.edit_img);
        }
    }
}
