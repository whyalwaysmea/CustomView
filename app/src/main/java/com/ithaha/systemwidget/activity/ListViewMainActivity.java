package com.ithaha.systemwidget.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ithaha.systemwidget.ListView.ChatItemListViewTest;
import com.ithaha.systemwidget.ListView.FlexibleListViewTest;
import com.ithaha.systemwidget.ListView.FocusListViewTest;
import com.ithaha.systemwidget.ListView.HideToolbarActivity;
import com.ithaha.systemwidget.ListView.NotifyTest;
import com.ithaha.systemwidget.ListView.ScrollHideListView;
import com.ithaha.systemwidget.R;


public class ListViewMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_listview);
    }

    public void btnViewHolder(View view) {
        startActivity(new Intent(this, NotifyTest.class));
    }

    public void btnChatItem(View view) {
        startActivity(new Intent(this, ChatItemListViewTest.class));
    }

    public void btnScrollHideListView(View view) {
        startActivity(new Intent(this, ScrollHideListView.class));
    }

    public void btnFlexible(View view) {
        startActivity(new Intent(this, FlexibleListViewTest.class));
    }

    public void btnFocus(View view) {
        startActivity(new Intent(this, FocusListViewTest.class));
    }

    public void hideToolbar(View view) {
        startActivity(new Intent(this, HideToolbarActivity.class));
    }
}
