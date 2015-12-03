package com.ithaha.systemwidget;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TopBar topBar = (TopBar) findViewById(R.id.my_topbar);
        topBar.setButtonVisable(1, false);
        topBar.setmListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this, "Left Click", Toast.LENGTH_LONG).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this, "Right Click", Toast.LENGTH_SHORT).show();
            }
        });


        CircleProgressView myProgress = (CircleProgressView) findViewById(R.id.my_circleprogress);
        myProgress.setShowText("Android");
    }


}
