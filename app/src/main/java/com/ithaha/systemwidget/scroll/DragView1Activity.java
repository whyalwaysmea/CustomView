package com.ithaha.systemwidget.scroll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.ithaha.systemwidget.R;

public class DragView1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_drag_view1);

        Button btnDragHelper = (Button) findViewById(R.id.draghelper);
        btnDragHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DragView1Activity.this, DragHelperActivity.class);
                startActivity(intent);
            }
        });
    }
}
