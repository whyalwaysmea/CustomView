package com.ithaha.systemwidget.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ithaha.systemwidget.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Long
 * 下拉刷新
 * on 2016/3/22.
 */
public class SwipeRefresh extends Fragment {

    @Bind(R.id.ultimate_recycler_view)
    UltimateRecyclerView ultimateRecyclerView;

    private ArrayList<String> mDatas;
    private Context mContext;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swiperefresh_layout, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        initData();
        return view;
    }

    public void initData() {

        mDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDatas.add(i+"");
        }

        final HomeAdapter homeAdapter = new HomeAdapter(getContext(), mDatas);

        linearLayoutManager = new LinearLayoutManager(mContext);
        ultimateRecyclerView.setLayoutManager(linearLayoutManager);
        ultimateRecyclerView.setAdapter(homeAdapter);

        // 设置下拉刷新的颜色
        ultimateRecyclerView.setDefaultSwipeToRefreshColorScheme(Color.parseColor("#ffffff"));

        // 下拉刷新
        ultimateRecyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas.clear();
                        for (int i = 0; i < 10; i++) {
                            mDatas.add(i+":" + "刷新的数据");
                        }
                        homeAdapter.notifyDataSetChanged();
                        ultimateRecyclerView.setRefreshing(false);
                    }
                }, 10000);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
