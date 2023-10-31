package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private List<Record> timeRecord = new ArrayList<>();

    static class MyHolder extends RecyclerView.ViewHolder{
        TextView record;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            //初始化控件
            record = (TextView) itemView.findViewById(R.id.recordTime);

        }
    }
    public MyAdapter(List<Record> list){
        this.timeRecord = list;
    }
    @NonNull
    @Override
    public MyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clock_item,null);
        MyAdapter.MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyHolder holder, int position) {
        //绑定数据
        Record recordInfo = timeRecord.get(position);
        //设置数据
        holder.record.setText(recordInfo.getCurrent_time());
    }

    @Override
    public int getItemCount() {
        return timeRecord.size();
    }


}