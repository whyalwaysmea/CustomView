package com.whyalwaysmea.myview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class ProgressBarActivity extends AppCompatActivity {

    private HorizontalProgressBar mProgressBar;
    private static final int MSG_PROGRESS_UPDATE = 0x110;


    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int progress = mProgressBar.getProgress();
            mProgressBar.setProgress(++progress);
            if (progress >= 100) {
                mHandler.removeMessages(MSG_PROGRESS_UPDATE);
            }
            mHandler.sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE, 1000);
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_layout);

        mProgressBar = (HorizontalProgressBar) findViewById(R.id.progress);
        mHandler.sendEmptyMessage(MSG_PROGRESS_UPDATE);
    }
}
