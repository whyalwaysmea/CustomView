package com.ithaha.systemwidget.expandabletextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ithaha.systemwidget.R;

public class ExpandableTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_textview);
        ExpandableTextView expandableTextView = (ExpandableTextView) findViewById(R.id.expand_text_view);
        expandableTextView.setText(getString(R.string.dummy_text1));

    }
}
