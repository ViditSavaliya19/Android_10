package com.example.android_10_std.Screen.Realtime_Screen.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android_10_std.R;
import com.example.android_10_std.Screen.Realtime_Screen.Model.ModelData;
import com.example.android_10_std.Screen.Realtime_Screen.controller.FirebaseRealTime;
import com.example.android_10_std.Screen.Realtime_Screen.controller.MyAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class realtime_Activity extends AppCompatActivity {

    private Button insertData;
    private RecyclerView rv_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime);
        binding();
        FirebaseRealTime realTime = new FirebaseRealTime(realtime_Activity.this);


        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rv(FirebaseRealTime.modelDataList);
            }
        });

    }

    void binding() {
        insertData = findViewById(R.id.insertData);
        rv_view = findViewById(R.id.rv_view);
    }

    void Rv(List<ModelData> list) {
        MyAdapter myAdapter = new MyAdapter(realtime_Activity.this, list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(realtime_Activity.this);
        rv_view.setLayoutManager(layoutManager);
        rv_view.setAdapter(myAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRealTime realTime = new FirebaseRealTime(realtime_Activity.this);
        realTime.ReadData();

    }
}

