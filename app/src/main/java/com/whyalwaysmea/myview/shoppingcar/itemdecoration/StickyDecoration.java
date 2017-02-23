package com.whyalwaysmea.myview.shoppingcar.itemdecoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import com.whyalwaysmea.myview.R;

/**
 * Created by HelloWorld on 2017/2/23.
 * onDraw()：可以实现类似绘制背景的效果，item的内容在上面。
 * onDrawOver()：可以绘制在内容的上面，覆盖item的内容
 * getItemOffsets()：可以实现类似padding的效果
 * 先执行ItemDecoration的onDraw()、再执行子ItemView的onDraw()、再执行ItemDecoration的onDrawOver()。
 */

public class StickyDecoration extends RecyclerView.ItemDecoration {

    private int topHeight;
    private TextPaint textPaint;
    private Paint paint;

    public StickyDecoration(Context context) {
        Resources resources = context.getResources();
        topHeight = resources.getDimensionPixelSize(R.dimen.top);

        paint = new Paint();
        paint.setColor(resources.getColor(R.color.colorAccent));
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(60);
        textPaint.setColor(Color.WHITE);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        System.out.println("StickyDecoration ==> getItemOffsets");
        // 获取当前View的position
        int position = parent.getChildAdapterPosition(view);
        System.out.println("position == " + position);
        if(isFirstInGroup(position)) {
            outRect.top = topHeight;
        } else {
            outRect.top = 0;
        }
    }

    private boolean isFirstInGroup(int position) {
        boolean isFirst = false;
        if(position == 0 || position == 15) {
            isFirst = true;
        }

        return isFirst;
    }

    private boolean isLastInGroup(int position) {
        boolean isLast = false;
        if(position == 14) {
            isLast = true;
        }
        return isLast;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            String textLine = "菜名";
            if (position == 0 || isFirstInGroup(position)) {
                float top = view.getTop() - topHeight;
                float bottom = view.getTop();
                c.drawRect(left, top, right, bottom, paint);//绘制红色矩形
                c.drawText(textLine, left + 30, bottom - 20, textPaint);//绘制文本
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int position = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        String label = "我是标题";
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            //判断item是不是该组的最后一个元素
            if (isLastInGroup(position)) {
                int bottom = child.getBottom();
                //滑动过程中，若分组中最后一个item的bottom小于label的高度，就把label的绘制位置往上提。
                if (bottom <= topHeight) {
                    c.drawRect(left, 0, right, bottom, paint);
                    c.drawText(label, 30, topHeight / 2 + (float) topHeight / 4 - (topHeight - bottom), textPaint);
                    //以下代码不再执行，免得出现两次绘制。
                    return;
                }
            }
        }
        c.drawRect(left, 0, right, topHeight, paint);
        c.drawText(label, 30, topHeight / 2 + (float) topHeight / 4, textPaint);
    }

}
