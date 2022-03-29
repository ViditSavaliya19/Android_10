package com.example.android_10_std.Screen.Api_Screen;

import static com.example.android_10_std.Utils.Api.ApiClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.android_10_std.R;
import com.example.android_10_std.Screen.Api_Screen.Model.Api_Model;
import com.example.android_10_std.Utils.Api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
    }

    void ApiCalling(String n2,String j1)
    {
        ApiInterface apiInterface=getRetrofit().create(ApiInterface.class);
        apiInterface.postData(n2,j1).enqueue(new Callback<Api_Model>() {
            @Override
            public void onResponse(Call<Api_Model> call, Response<Api_Model> response) {
                Toast.makeText(DataActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Api_Model> call, Throwable t) {
                Toast.makeText(DataActivity.this, " "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}