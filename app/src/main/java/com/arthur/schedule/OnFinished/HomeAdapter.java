package com.arthur.schedule.OnFinished;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arthur.schedule.R;
import com.arthur.schedule.bean.Data;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zhangyu on 26/09/2017.
 */
public class HomeAdapter extends RecyclerView.Adapter{


    ArrayList<Data> mDatas;
    Context context;

    public HomeAdapter(ArrayList<Data> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = new MyHolder(LayoutInflater.from(
                context).inflate(R.layout.item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.title.setText(mDatas.get(position).getTitle());
        myHolder.end_date.setText(mDatas.get(position).getEndDate().toString());
        myHolder.content.setText(mDatas.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView end_date;
        TextView content;

        public MyHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title1);
            end_date = (TextView) itemView.findViewById(R.id.end_date);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
