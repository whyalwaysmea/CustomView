package com.ithaha.systemwidget.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ithaha.systemwidget.R;

public class CircleProgressViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress_view);
        
        com.ithaha.systemwidget.view.CircleProgressView myProgress = (com.ithaha.systemwidget.view.CircleProgressView) findViewById(R.id.my_circleprogress);
        myProgress.setShowText("Android");
    }
}
