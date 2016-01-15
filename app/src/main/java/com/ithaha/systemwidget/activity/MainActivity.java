package com.ithaha.systemwidget.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ithaha.systemwidget.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.view_activity)
    Button viewActivity;
    @Bind(R.id.listview_activity)
    Button listviewActivity;
    @Bind(R.id.scroll_activity)
    Button scrollActivity;
    @Bind(R.id.floorview_activity)
    Button floorviewActivity;

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

        scrollActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ScrollActivity.class);
            }
        });

        floorviewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FloorViewActivity.class);
            }
        });
    }

    private void startActivity(Class thiz) {
        Intent intent = new Intent(MainActivity.this, thiz);
        startActivity(intent);
    }
}
