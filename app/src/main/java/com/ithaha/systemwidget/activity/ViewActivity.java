package com.ithaha.systemwidget.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ithaha.systemwidget.R;
import com.ithaha.systemwidget.view.CircleProgressViewActivity;
import com.ithaha.systemwidget.view.MyTextViewActivity;
import com.ithaha.systemwidget.view.ScrollViewActivity;
import com.ithaha.systemwidget.view.ShineTextViewActivity;
import com.ithaha.systemwidget.view.TopBarActivity;
import com.ithaha.systemwidget.view.VolumeViewActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ViewActivity extends Activity {


    @Bind(R.id.btn_my_textview)
    Button btnMyTextview;
    @Bind(R.id.btn_shine_textview)
    Button btnShineTextview;
    @Bind(R.id.btn_topbar)
    Button btnTopbar;
    @Bind(R.id.btn_circle_progress)
    Button btnCircleProgress;
    @Bind(R.id.btn_volume)
    Button btnVolume;
    @Bind(R.id.btn_viewgroup)
    Button btnViewgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);

        btnMyTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyTextViewActivity.class));
            }
        });

        btnShineTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShineTextViewActivity.class));
            }
        });

        btnTopbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TopBarActivity.class));
            }
        });

        btnCircleProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CircleProgressViewActivity.class));
            }
        });

        btnVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), VolumeViewActivity.class));
            }
        });

        btnViewgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScrollViewActivity.class));
            }
        });
    }


}
