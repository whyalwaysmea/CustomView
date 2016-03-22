package com.ithaha.systemwidget.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ithaha.systemwidget.R;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.ArrayList;

/**
 * Created by Long
 * on 2016/3/22.
 */
public class HomeAdapter extends UltimateViewAdapter<HomeAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<String> mDatas;

    public HomeAdapter(Context context, ArrayList<String> list) {
        this.mContext = context;
        this.mDatas = list;
    }

    @Override
    public MyViewHolder getViewHolder(View view) {
        return new MyViewHolder(view);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.chat_item_itemin, parent,
                false));
        return holder;
    }

    @Override
    public int getAdapterItemCount() {
        return mDatas.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.text_in);
        }
    }
}
