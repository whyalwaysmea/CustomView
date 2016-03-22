package com.ithaha.systemwidget.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
 * on 2016/3/22.
 */
public class SwipeRefresh extends Fragment {

    @Bind(R.id.ultimate_recycler_view)
    UltimateRecyclerView ultimateRecyclerView;

    private ArrayList<String> mDatas;
    private Context mContext;

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

        HomeAdapter homeAdapter = new HomeAdapter(getContext(), mDatas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        ultimateRecyclerView.setLayoutManager(linearLayoutManager);
        ultimateRecyclerView.setAdapter(homeAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
