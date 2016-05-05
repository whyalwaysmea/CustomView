package com.ithaha.systemwidget.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ithaha.systemwidget.R;
import com.ithaha.systemwidget.floorview.Comment;
import com.ithaha.systemwidget.floorview.FloorView;
import com.ithaha.systemwidget.floorview.SubComments;
import com.ithaha.systemwidget.floorview.SubFloorFactory;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FloorViewActivity extends AppCompatActivity {

    @Bind(R.id.floors_parent)
    FloorView floorsParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_view);
        ButterKnife.bind(this);

        ArrayList<Comment> commentArrayList = new ArrayList<>();
        commentArrayList.add(new Comment("heihei", "嘿嘿嘿，我是评论", 2));
        commentArrayList.add(new Comment("haha", "哈哈哈，我是评论", 1));
        commentArrayList.add(new Comment("wowo", "喔喔喔，我是评论", 3));
        commentArrayList.add(new Comment("wowo", "喔喔喔，我是评论", 4));
        commentArrayList.add(new Comment("wowo", "喔喔喔，我是评论", 5));
        commentArrayList.add(new Comment("wowo", "喔喔喔，我是评论", 6));
        commentArrayList.add(new Comment("wowo", "喔喔喔，我是评论", 7));
        commentArrayList.add(new Comment("wowo", "喔喔喔，我是评论", 8));
        commentArrayList.add(new Comment("wowo", "喔喔喔，我是评论", 9));

        Collections.sort(commentArrayList);

        SubComments subComments = new SubComments(commentArrayList);
        floorsParent.setComments(subComments);
        floorsParent.setFactory(new SubFloorFactory());
        floorsParent.setBoundDrawer(getResources().getDrawable(R.drawable.bg_comment));
        floorsParent.init();
    }

}
