package com.example.android_10_std.Screen.Realtime_Screen.controller;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.android_10_std.Screen.Realtime_Screen.Model.ModelData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRealTime {
    Activity activity;
    public static  List<ModelData> modelDataList=new ArrayList<>();

    public FirebaseRealTime(Activity activity)
    {
        this.activity=activity;
    }

   public void ReadData()
    {
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= firebaseDatabase.getReference();

        databaseReference.child("Student").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren())
                    {
                        String key = dataSnapshot.getKey();
                        String grid = dataSnapshot.child("grid").getValue().toString();
                        String name = dataSnapshot.child("name").getValue().toString();
                        String mobile = dataSnapshot.child("mobile").getValue().toString();

                        ModelData modelData = new ModelData(name,grid,mobile,key);
                        modelDataList.add(modelData);
                        Log.e("TAG", "onDataChange: "+modelDataList.get(0).getName() );

                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(activity, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
