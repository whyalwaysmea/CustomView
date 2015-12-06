package com.ithaha.systemwidget.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.ithaha.systemwidget.R;
import com.ithaha.systemwidget.view.TopBar;

public class TopBarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_bar);

        TopBar topBar = (TopBar) findViewById(R.id.my_topbar);
        topBar.setButtonVisable(1, false);
        topBar.setmListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(TopBarActivity.this, "Left Click", Toast.LENGTH_LONG).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(TopBarActivity.this, "Right Click", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
