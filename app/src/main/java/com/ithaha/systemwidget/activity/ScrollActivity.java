package com.ithaha.systemwidget.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ithaha.systemwidget.R;
import com.ithaha.systemwidget.scroll.DragView1Activity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScrollActivity extends AppCompatActivity {

    @Bind(R.id.drawview1)
    Button drawview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        ButterKnife.bind(this);

        drawview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScrollActivity.this, DragView1Activity.class));
            }
        });
    }
}
