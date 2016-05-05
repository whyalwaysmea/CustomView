package com.ithaha.systemwidget.ListView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ithaha.systemwidget.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 这种方法只支持RecyclerView
 */
public class HideToolbarActivity extends AppCompatActivity {

	private RecyclerView mRecyclerView;
	private HomeAdapter mAdapter;
	private List<String> mDatas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hide_toolbar);


		initData();
		mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
		//设置布局管理器
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		//设置adapter
		mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
		//设置Item增加、移除动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());

	}

	protected void initData() {
		mDatas = new ArrayList<String>();
		for (int i = 'A'; i < 'z'; i++) {
			mDatas.add("" + (char) i);
		}
	}


	class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{


		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(HideToolbarActivity.this)
					.inflate(android.R.layout.simple_expandable_list_item_1, parent, false));
			return myViewHolder;
		}

		@Override
		public void onBindViewHolder(MyViewHolder holder, int position) {
			holder.tv.setText(mDatas.get(position));
		}

		@Override
		public int getItemCount() {
			return mDatas.size();
		}

		class MyViewHolder extends RecyclerView.ViewHolder {

			protected TextView tv;

			public MyViewHolder(View itemView) {
				super(itemView);
				tv =  (TextView) itemView.findViewById(android.R.id.text1);
			}
		}
	}
}
