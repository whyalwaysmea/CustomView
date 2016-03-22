package com.ithaha.systemwidget.recyclerview;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.ithaha.systemwidget.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 第三方RecyclerView的使用
 */
public class RecyclerViewActivity extends AppCompatActivity {

    @Bind(R.id.recycler_framelayout)
    FrameLayout recyclerFramelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.recycler_framelayout, new SwipeRefresh());
        fragmentTransaction.commit();
    }
}
