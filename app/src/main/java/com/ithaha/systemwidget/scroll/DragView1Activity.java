package com.ithaha.systemwidget.scroll;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.ithaha.systemwidget.R;

public class DragView1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_drag_view1);
    }
}
