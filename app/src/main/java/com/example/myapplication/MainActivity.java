package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView timeOut;
    private MyAdapter adapter;
    private List<Record> recordList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化RecyclerView和current_time等
        timeOut = findViewById(R.id.current_time);
        recyclerView = findViewById(R.id.timetable);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(recordList);
        recyclerView.setAdapter(adapter);

        //初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://632a6f811090510116c05b32.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //创建Retrofit接口
        ApiService apiService = retrofit.create(ApiService.class);

        //执行网络请求
        Call<Record> call = apiService.getRecords();
        call.enqueue(new Callback<Record>() {
            @Override
            public void onResponse(Call<Record> call, Response<Record> response) {
                if (response.isSuccessful()) {
                    Record recordData = response.body();
                    if (recordData != null && recordData.getTimelist() != null) {
                        List<String> timelist = recordData.getTimelist();
                        for (String time : timelist) {
                            recordList.add(new Record(time));
                        }
                        //输出timelist和current_time
                        timeOut.setText(recordData.getCurrent_time());
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<Record> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}