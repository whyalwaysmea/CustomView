package com.ithaha.systemwidget.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/1/12.
 */
public class DragView2 extends View {

    private int lastX;
    private int lastY;

    public DragView2(Context context) {
        super(context);
    }

    public DragView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录触摸点坐标
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                // 在当前left, top, right, bottom  的基础上加上偏移量
                layout(getLeft() +offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);

                break;
        }
        return true;
    }
}
