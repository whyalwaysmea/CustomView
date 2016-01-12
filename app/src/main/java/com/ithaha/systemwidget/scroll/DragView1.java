package com.ithaha.systemwidget.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/1/12.
 */
public class DragView1 extends View {

    private int lastX;
    private int lastY;

    public DragView1(Context context) {
        super(context);
    }

    public DragView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录触摸点坐标
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算偏移量
                int offsetX = rawX - lastX;
                int offsetY = rawY - lastY;
                // 在当前left, top, right, bottom  的基础上加上偏移量
                layout(getLeft() +offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);

//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                // 重新设置初始坐标
                lastX = rawX;
                lastY = rawY;
                break;
        }
        return true;
    }
}
