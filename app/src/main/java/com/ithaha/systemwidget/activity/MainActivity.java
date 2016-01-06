package com.ithaha.systemwidget.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ithaha.systemwidget.ListView.ListViewMainActivity;
import com.ithaha.systemwidget.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	@Bind(R.id.view_activity)
	Button viewActivity;
	@Bind(R.id.listview_activity)
	Button listviewActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		viewActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(ViewActivity.class);
			}
		});

		listviewActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(ListViewMainActivity.class);
			}
		});
	}

	private void startActivity(Class thiz) {
		Intent intent = new Intent(MainActivity.this, thiz);
		startActivity(intent);
	}
}
